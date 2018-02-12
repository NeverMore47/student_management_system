<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="学生姓名" v-model="listQuery.studentRealName">
      </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="学生编号" v-model="listQuery.studentNo">
      </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="奖惩标题" v-model="listQuery.studentNo">
      </el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='学号' width="120">
        <template slot-scope="scope">
          {{scope.$index}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="200">
        <template slot-scope="scope">
          <span>王小新</span>
        </template>
      </el-table-column>
      <el-table-column label="奖惩内容" width="110" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.author}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.start"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog title="修改" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="学号">
          <el-input v-model="temp.studentNo" v-bind:disabled="dialogDicDisabled"></el-input>
        </el-form-item>
        <el-form-item label="学生名">
          <el-input v-model="temp.studentRealName" v-bind:disabled="dialogDicDisabled"></el-input>
        </el-form-item>
        <el-form-item label="奖惩标题" prop="RE_PU_NAME">
          <el-input v-model="temp.RE_PU_NAME"></el-input>
        </el-form-item>
        <el-form-item label="奖惩内容" prop="RE_PU_DESC">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="添加奖惩记录" v-model="temp.RE_PU_DESC">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">提交</el-button>
        <el-button v-else type="primary" @click="updateData">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findRewardsAndPunishmentList } from '@/api/student'

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
        start: 1,
        rows: 20,
        studentRealName: undefined,
        studentNo: undefined,
        type: undefined
      },
      dialogDicDisabled: false,
      temp: {
        id: undefined,
        studentNo: undefined,
        studentRealName: undefined,
        RE_PU_DESC: undefined,
        RE_PU_NAME: undefined
      },
       rules: {
        RE_PU_DESC: [{ required: true, message: '奖惩记录不能为空', trigger: 'change' }],
        RE_PU_NAME: [{ required: true, message: '奖惩标题不能为空', trigger: 'change' }]
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
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      findRewardsAndPunishmentList(this.listQuery).then(response => {
        this.list = response.data.items
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      if (this.listQuery.start === val) {
        return
      }
      this.listQuery.start = val
      this.fetchData()
    },
    handleSizeChange(val) {
      if (this.listQuery.rows === val) {
        return
      }
      this.listQuery.rows = val
      this.fetchData()
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
      this.listQuery.start = 1
      this.fetchData()
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
