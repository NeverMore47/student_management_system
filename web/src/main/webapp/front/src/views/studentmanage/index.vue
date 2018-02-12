<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="学生编号" v-model="listQuery.studentNo">
      </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="真实姓名" v-model="listQuery.studentRealName">
      </el-input>
      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.studentClassId" placeholder="选择班级">
        <el-option label="一班" value="1"></el-option>
        <el-option label="二班" value="2"></el-option>
        <el-option label="三班" value="3"></el-option>
        <el-option label="四班" value="4"></el-option>
        <el-option label="五班" value="5"></el-option>
      </el-select>

      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加用户</el-button>
      <a href="/studentInfo/downloadTemplate" class="el-button filter-item el-button--primary">下载模版</a>
      <form id="uploadFile" method="post" enctype="multipart/form-data" action="/studentInfo/uploadStudentInfo" style="position: relative; display: inline-block;">
        <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit">导入数据</el-button>
        <input type="file" class="filter-item" @change="pushImg" accept="image/jpeg,image/png,image/gif" style="width: 117px;height: 40px;position: absolute;top: 0;right: 0;opacity: 0;" />
      </form>
      
    </div>


    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='编号' width="200">
        <template slot-scope="scope">
          {{scope.row.studentNo}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='班级' width="120">
        <template slot-scope="scope">
          {{scope.row.studentClassId | classFilter}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.studentRealName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" width="120">
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
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          <el-button size="mini"  @click="handleCreateDisciplinary(scope.row)" type="default" icon="el-icon-edit">添加学生奖惩记录</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
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
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
            <el-radio :label="0">未知</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="出生日期" v-model="temp.studentBirthDate"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="籍贯" prop="studentNativePlace">
          <el-input v-model="temp.studentNativePlace"></el-input>
        </el-form-item>
        <el-form-item label="所在班级">
          <el-select v-model="temp.departmentId" placeholder="所在系" style="width: 49%;">
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


    <el-dialog title="添加奖惩记录" :visible.sync="dialogDicVisible">
      <el-form :rules="rules" ref="dataFormDic" :model="tempDic" label-position="left" label-width="80px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="学号">
          <el-input v-model="tempDic.studentNo" v-bind:disabled="dialogDicDisabled"></el-input>
        </el-form-item>
        <el-form-item label="学生名">
          <el-input v-model="tempDic.studentRealName" v-bind:disabled="dialogDicDisabled"></el-input>
        </el-form-item>
        <el-form-item label="奖惩标题" prop="rePuName">
          <el-input v-model="tempDic.rePuName" placeholder="添加奖惩标题"></el-input>
        </el-form-item>
        <el-form-item label="奖惩时间" prop="rePuDate">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="奖惩时间" v-model="tempDic.rePuDate"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="奖惩内容" prop="rePuDesc">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="添加奖惩记录" v-model="tempDic.rePuDesc">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogDicVisible = false">取消</el-button>
        <el-button  type="primary" @click="createDateDisciplinary">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { findStudentInfoList, saveStudentInfo, deleteStudentInfo, saveRewardsAndPunishment } from '@/api/student'

export default {
  data() {
    return {
      list: null,
      total: 20,
      listLoading: true,
      listQuery: {
        page: 1,
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
        studentGender: 1,
        studentBirthDate: new Date(),
        studentNativePlace: '',
        departmentId: '计算机及应用',
        studentClassId: '一班',
        studentHealthStatus: '',
        studentPlaceGoTo: ''
      },
      dialogDicVisible: false,
      dialogDicDisabled: true,
      tempDic: {
        studentId: undefined,
        studentNo: undefined,
        studentRealName: undefined,
        rePuDesc: undefined,
        rePuDate: new Date(),
        rePuName: undefined
      },
      rules: {
        studentNo: [{ required: true, message: '编号不能为空', trigger: 'change' }],
        studentRealName: [{ required: true, message: '姓名不能为空', trigger: 'change' }],
        rePuDesc: [{ required: true, message: '奖惩记录不能为空', trigger: 'change' }],
        rePuName: [{ required: true, message: '奖惩标题不能为空', trigger: 'change' }],
        rePuDate: [{ type: 'date', required: true, message: '请选择奖惩时间', trigger: 'change' }]
      },
      downloadLoading: false,
      
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
    },
    classFilter(classid) {
      const classMap = {
        1: '一班',
        2: '二班',
        3: '三班',
        4: '四班',
        5: '五班'
      }
      return classMap[classid]
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
      this.listQuery.page = val
      this.fetchData()
    },
    handleSizeChange(val) {
      if (this.listQuery.rows === val) {
        return
      }
      this.listQuery.rows = val
      this.fetchData()
    },
    xTransition(xname, type) {
      const tempType = type ? type : 'default'
      var nameMap = {}
      if(tempType === 'default') {
        nameMap = {
          1: '电子学与信息系统',
          2: '无线电物理学',
          3: '物理电子学',
          4: '计算机软件',
          5: '计算机及应用'
        }
      } else {
        nameMap = {
          '电子学与信息系统' :1,
          '无线电物理学' :2,
          '物理电子学' :3,
          '计算机软件' :4,
          '计算机及应用' :5
        }
      }
      return nameMap[xname] 
    },
    classTransition(cname, type) {
      const tempType = type ? type : 'default'
      var nameMap = {}
      if(tempType === 'default') {
        nameMap = {
          1: '一班',
          2: '二班',
          3: '三班',
          4: '四班',
          5: '五班'
        }
      } else {
        nameMap = {
          '一班' :1,
          '二班' :2,
          '三班' :3,
          '四班' :4,
          '五班' :5
        }
      }
      return nameMap[cname] 
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        studentNo: '',
        studentRealName: '',
        studentGender: 1,
        studentBirthDate: new Date(),
        studentNativePlace: '',
        departmentId: '计算机及应用',
        studentClassId: '一班',
        studentHealthStatus: '',
        studentPlaceGoTo: ''
      }
    },
    handleFilter() {
      this.listQuery.page = 1
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
          const tempData = Object.assign({}, this.temp)
          var date = new Date(tempData.studentBirthDate)
          var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
          tempData.studentBirthDate = dateInfo
          tempData.departmentId = this.xTransition(tempData.departmentId, 'name')
          tempData.studentClassId = this.classTransition(tempData.studentClassId, 'name')
          tempData.type = 1
          console.log(tempData)
          saveStudentInfo(tempData).then(response => {
            this.dialogFormVisible = false
            if (!response.success) {
              this.$notify({
                title: '失败',
                message: response.message,
                type: 'error',
                duration: 2000
              })
              return
            }
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })

            this.fetchData()
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.departmentId = this.xTransition(this.temp.departmentId)
      this.temp.studentClassId = this.classTransition(this.temp.studentClassId)
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
          var date = new Date(tempData.studentBirthDate)
          var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
          tempData.studentBirthDate = dateInfo
          tempData.departmentId = this.xTransition(tempData.departmentId, 'name')
          tempData.studentClassId = this.classTransition(tempData.studentClassId, 'name')
          tempData.type = 2
          saveStudentInfo(tempData).then(response => {
            this.dialogFormVisible = false
            if(!response.success) {
              this.$notify({
                title: '失败',
                message: response.message,
                type: 'error',
                duration: 2000
              })
              return
            }
            this.temp.studentClassId = this.classTransition(this.temp.studentClassId, 'name')
            for (const v of this.list) {
              if(v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.$notify({
              title: '成功',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      // 删除
      const id = {
        id: row.id
      }
      deleteStudentInfo(id).then(response => {
        if(!response.success) {
          this.$notify({
            title: '失败',
            message: response.message,
            type: 'error',
            duration: 2000
          })
          return
        }
        const index = this.list.indexOf(row)
        this.list.splice(index, 1)
      })
    },
    resetDicTemp() {
      this.tempDic = {
        studentNo: undefined,
        studentRealName: undefined,
        remark: undefined
      }
    },
    handleCreateDisciplinary(row) {
      // 添加奖惩记录
      this.tempDic.studentNo = row.studentNo
      this.tempDic.studentId = row.id
      this.tempDic.studentRealName = row.studentRealName
      this.tempDic.rePuName = undefined
      this.tempDic.rePuDesc = undefined
      this.tempDic.rePuDate = new Date()
      this.dialogDicVisible = true
      this.$nextTick(() => {
        this.$refs['dataFormDic'].clearValidate()
      })
    },
    createDateDisciplinary() {
      this.$refs['dataFormDic'].validate((valid) => {
        if (valid) {
          var date = new Date(this.tempDic.rePuDate)
          var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
          const tempData = {
            studentId: this.tempDic.studentId,
            rePuName: this.tempDic.rePuName,
            rePuDesc: this.tempDic.rePuDesc,
            rePuDate: dateInfo
          }
          saveRewardsAndPunishment(tempData).then(response => {
            console.log(response)
            this.dialogDicVisible = false
            if(!response.success) {
              this.$notify({
                title: '失败',
                message: response.message,
                type: 'error',
                duration: 2000
              })
              return
            }
            this.$notify({
              title: '成功',
              message: '添加成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    pushImg() {
       document.getElementById('uploadFile').submit()
    }
  }
}
</script>