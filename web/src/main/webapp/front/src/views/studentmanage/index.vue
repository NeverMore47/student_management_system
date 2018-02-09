<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="学生编号" v-model="listQuery.title">
      </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="真实姓名" v-model="listQuery.title">
      </el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加用户</el-button>
    </div>


    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='编号' width="200">
        <template slot-scope="scope">
          {{scope.row.studentNo}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.studentRealName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.studentGender | sexFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="籍贯" width="150" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.studentNativePlace}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" >
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">冻结</el-button>
          <el-button size="mini" type="default" @click="handleModifyStatus(scope.row,'deleted')">添加成绩</el-button>
          <el-button size="mini" type="default" @click="handleModifyStatus(scope.row,'deleted')">添加奖惩记录</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.rows"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.start" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="编号" prop="studentNo">
          <el-input v-model="temp.studentNo"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="studentRealName">
          <el-input v-model="temp.studentRealName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="studentGender">
          <el-radio-group v-model="temp.studentGender">
            <el-radio label="1">男</el-radio>
            <el-radio label="2">女</el-radio>
            <el-radio label="0">未知</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期" prop="studentBirthDate">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="出生日期" v-model="temp.studentBirthDate"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="籍贯" prop="studentNativePlace">
          <el-input v-model="temp.studentNativePlace"></el-input>
        </el-form-item>
        <el-form-item label="所在班级">
          <el-select v-model="temp.department" placeholder="所在系" style="width: 49%;">
            <el-option label="电子学与信息系统" value="电子学与信息系统"></el-option>
            <el-option label="无线电物理学" value="无线电物理学"></el-option>
            <el-option label="物理电子学" value="物理电子学"></el-option>
            <el-option label="计算机软件" value="计算机软件"></el-option>
            <el-option label="计算机及应用" value="计算机及应用"></el-option>
          </el-select>
          <el-select v-model="temp.studentClassId" placeholder="所在班级" style="width: 49%;">
            <el-option label="一班" value="一班"></el-option>
            <el-option label="二班" value="二班"></el-option>
            <el-option label="三班" value="三班"></el-option>
            <el-option label="四班" value="四班"></el-option>
            <el-option label="五班" value="五班"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="健康状况">
          <el-input v-model="temp.studentHealthStatus"></el-input>
        </el-form-item>
        <el-form-item label="毕业去向">
          <el-input type="textarea" v-model="temp.studentPlaceGoTo"></el-input>
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
import { findStudentInfoList } from '@/api/student'

export default {
  data() {
    return {
      list: null,
      total: 20,
      listLoading: true,
      listQuery: {
        start: 1,
        rows: 20,
        studentNo: undefined,
        studentRealName: undefined,
        studentClassId: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '添加'
      },
      temp: {
        id: undefined,
        studentNo: '',
        studentRealName: '',
        studentGender: '',
        studentBirthDate: new Date(),
        studentNativePlace: '',
        department: '计算机及应用',
        studentClassId: '一班',
        studentHealthStatus: '',
        studentPlaceGoTo: ''
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        studentNo: [{ required: true, message: '编号不能为空', trigger: 'change' }],
        studentRealName: [{ required: true, message: '姓名不能为空', trigger: 'change' }]
      },
      downloadLoading: false
    }
  },
  filters: {
    sexFilter(status) {
      const statusMap = {
        1: '男',
        2: '女',
        0: '未知'
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
      findStudentInfoList(this.listQuery).then(response => {
        this.list = response.data.infoList
        this.total = response.data.count
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      if (this.listQuery.rows === val) {
        return
      }
      this.listQuery.rows = val
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
        idcard: '',
        name: '',
        sex: '',
        date1: new Date(),
        place: '',
        department: '计算机及应用',
        class: '一班',
        health: '',
        direction: ''
      }
    },
    handleFilter() {
      this.listQuery.rows = 1
      this.getList()
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