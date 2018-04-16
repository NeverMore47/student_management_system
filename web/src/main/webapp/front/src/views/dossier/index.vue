<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="学生姓名" v-model="listQuery.studentRealName">
            </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="学生编号" v-model="listQuery.studentNo">
      </el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <!-- <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加成绩</el-button> -->
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='学号' width="120">
        <template slot-scope="scope">
          {{scope.row.studentNo}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.studentRealName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" width="200" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.studentPhoneNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="政治面貌" width="200" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.politicalStatus}}</span>
        </template>
      </el-table-column>
      <el-table-column label="详细地址" width="200" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.studentAdress}}</span>
        </template>
      </el-table-column>
      
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
        <el-button type="default" size="mini" @click="handleLook(scope.row)">查看详情</el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">添加毕业证书</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>


    <el-dialog title="查看详情" :visible.sync="dialogFormVisibleLook">
        <el-form ref="dataForm1" :model="temp1" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
          <el-form-item label="学号">{{temp1.studentNo}}</el-form-item>
          <el-form-item label="姓名">{{temp1.studentRealName}}</el-form-item>
          <el-form-item label="班级">{{temp1.studentClassId | classFilter}}</el-form-item>
          <el-form-item label="籍贯">{{temp1.studentNativePlace}}</el-form-item>
          <el-form-item label="毕业去向">{{temp1.studentPlaceGoTo}}</el-form-item>
          <el-form-item label="成绩">
            <el-table :data="temp1.cerList" border fit highlight-current-row>
              <el-table-column align="center" label='课程名' width="200">
                <template slot-scope="scope">
                  {{scope.row.courseId | courseFilter}}
                </template>
              </el-table-column>
              <el-table-column align="center" label='成绩' width="120">
                <template slot-scope="scope">
                  {{scope.row.resultScore}}
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item label="奖惩记录">
            <div v-for="rap in temp1.raprList"> {{ rap.rePuName }}</div>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisibleLook = false">确定</el-button>
        </div>
      </el-dialog>


    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="学号">
            <el-input v-bind:disabled="disabledForm" v-model="temp.studentNo" aria-disabled=""></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-bind:disabled="disabledForm" v-model="temp.studentRealName" aria-disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="联系方式">
            <el-input v-bind:disabled="disabledForm" v-model="temp.studentPhoneNum" aria-disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-bind:disabled="disabledForm" v-model="temp.studentAdress" aria-disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="政治面貌">
            <el-input v-bind:disabled="disabledForm" v-model="temp.politicalStatus" aria-disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="平均绩点">
            <el-input v-bind:disabled="disabledForm" v-model="temp.averagePoint" aria-disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="毕业时间">
            <el-input v-model="temp.graduationDate" aria-disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="证书编号">
            <el-input v-model="temp.graduationNo" aria-disabled="true"></el-input>
          </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateData">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findStudentExtendInfoList, updateStudentExtendInfo, findStudentAllInfoById } from '@/api/student'

export default {
  data() {
    return {
      disabledForm: true,
      list: null,
      total: 100,
      listLoading: true,
      dialogFormVisible: false,
      dialogFormVisibleLook: false,
      dialogStatus: '',
      listQuery: {
        page: 1,
        rows: 20,
        studentNo: '',
        studentRealName: ''
      },
      textMap: {
        update: '修改',
        create: '添加'
      },
      temp: {
        studentPhoneNum: '',
        studentAdress: '',
        politicalStatus: '',
        averagePoint: '',
        graduationDate: '',
        graduationNo: "",
        studentNo: '',
        studentRealName: ''
      },
      temp1: {
        studentPhoneNum: '',
        studentAdress: '',
        politicalStatus: '',
        averagePoint: '',
        graduationDate: '',
        graduationNo: "",
        studentNo: '',
        studentRealName: ''
      },
      downloadLoading: false
    }
  },
  filters: {
    classFilter(classid) {
      const classMap = {
        1: '信管一班',
        2: '信管二班',
        3: '信管三班',
        4: '信管四班',
        5: '信管五班'
      }
      return classMap[classid]
    },
    courseFilter(courseid) {
      const courseMap = {
        1: 'Web程序设计',
        2: '大学生就业指导',
        3: '分布式数据库应用',
        4: '数据结构课程设计'
      }
      return courseMap[courseid]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      findStudentExtendInfoList(this.listQuery).then(response => {
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
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        department: 'Web程序设计',
        grade: ''
      }
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchData()
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      var date = new Date(this.temp.graduationDate)
      var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      this.temp.graduationDate = dateInfo
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    handleLook(row) {
      this.dialogFormVisibleLook = true

      findStudentAllInfoById({id: row.studentId}).then(response => {
        console.log(response.data.studentExtendInfoVO)
        this.temp1 = Object.assign({}, response.data.studentExtendInfoVO)
      })
      // this.temp1 = Object.assign({}, row) // copy obj
      
      // var date = new Date(this.temp1.graduationDate)
      // var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      // this.temp1.graduationDate = dateInfo
    },

    updateData() {
      const tempData = {
        id: this.temp.id,
        graduationDate: this.temp.graduationDate,
        graduationNo: this.temp.graduationNo
      }
      updateStudentExtendInfo(tempData).then(response => {
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
        this.$notify({
          title: '成功',
          message: '更新成功',
          type: 'success',
          duration: 2000
        })
        this.fetchData()
      })
    }
  }
}
</script>
