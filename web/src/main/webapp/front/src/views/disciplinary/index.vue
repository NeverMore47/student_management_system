<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select clearable style="width: 200px" class="filter-item" v-model="importance" placeholder="课程名">
        <el-option label="操作系统" value="操作系统"></el-option>
        <el-option label="编译原理" value="编译原理"></el-option>
        <el-option label="数据结构与算法" value="数据结构与算法"></el-option>
        <el-option label="人工智能导论" value="人工智能导论"></el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='编号' width="120">
        <template slot-scope="scope">
          {{scope.$index}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="200">
        <template slot-scope="scope">
          <span>王小新</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="110" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.author}}</span>
        </template>
      </el-table-column>
      <el-table-column label="出生日期" width="250" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.display_time}}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="所在班级">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="操作" label="Display_time" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">冻结</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="学生名" prop="name">
          <el-input v-model="temp.name" aria-disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="课程名">
          <el-select v-model="temp.department" placeholder="课程" >
            <el-option label="操作系统" value="操作系统"></el-option>
            <el-option label="编译原理" value="编译原理"></el-option>
            <el-option label="数据结构与算法" value="数据结构与算法"></el-option>
            <el-option label="人工智能导论" value="人工智能导论"></el-option>
          </el-select>      
        </el-form-item>
        <el-form-item label="分数">
          <el-input v-model="temp.grade"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">提交</el-button>
        <el-button v-else type="primary" @click="updateData">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="Reading statistics" :visible.sync="dialogPvVisible">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel"> </el-table-column>
        <el-table-column prop="pv" label="Pv"> </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">提交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// import { getList } from '@/api/table'

export default {
  data() {
    return {
      id: '',
      importance: '操作系统',
      list: null,
      total: 100,
      listLoading: true,
      dialogFormVisible: false,
      dialogStatus: '',
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      textMap: {
        update: '修改',
        create: '添加'
      },
      temp: {
        id: undefined,
        name: '',
        department: '操作系统',
        grade: ''
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        grade: [{ required: true, message: '分数不能为空', trigger: 'change' }]
      },
      downloadLoading: false
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    // this.fetchData()
  },
  methods: {
    // fetchData() {
    //   this.listLoading = true
    //   getList(this.listQuery).then(response => {
    //     this.list = response.data.items
    //     this.listLoading = false
    //   })
    // }
    handleCurrentChange(val) {
      // if (this.listQuery.page === val) {
      //   return
      // }
      // this.listQuery.page = val
      // this.getList()
    },
    handleSizeChange(val) {
      // if (this.listQuery.limit === val) {
      //   return
      // }
      // this.listQuery.limit = val
      // this.getList()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        department: '操作系统',
        grade: ''
      }
    },
    handleFilter() {
      // this.listQuery.page = 1
      // this.getList()
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
          this.temp.author = 'vue-element-admin'
          // createArticle(this.temp).then(() => {
          //   this.list.unshift(this.temp)
          //   this.dialogFormVisible = false
          //   this.$notify({
          //     title: '成功',
          //     message: '创建成功',
          //     type: 'success',
          //     duration: 2000
          //   })
          // })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          // updateArticle(tempData).then(() => {
          //   for (const v of this.list) {
          //     if (v.id === this.temp.id) {
          //       const index = this.list.indexOf(v)
          //       this.list.splice(index, 1, this.temp)
          //       break
          //     }
          //   }
          //   this.dialogFormVisible = false
          //   this.$notify({
          //     title: '成功',
          //     message: '更新成功',
          //     type: 'success',
          //     duration: 2000
          //   })
          // })
        }
      })
    }
  }
}
</script>
