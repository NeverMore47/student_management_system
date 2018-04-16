<template>
  <div class="tab-container">
    <el-upload
        class="upload-demo el-button filter-item el-button--primary"
        :action="action"
        :on-change="handleChange"
        :on-success="handleSuccess"
        :on-error="handleError"
        :show-file-list="false">
        <el-button size="small" type="primary" >上传实习报告</el-button>
    </el-upload>
    <el-button type="primary" @click="dialogFormVisible = true">录入档案信息</el-button>
    <el-tabs style='margin-top:15px;' v-model="activeName" type="border-card">
      <el-tab-pane v-for="item in tabMapOptions" :label="item.label" :key='item.key' :name="item.key">
        <template v-if="activeName == item.key &&  item.key == 'in'">
            <el-form ref="form" :model="form" label-width="150px">
                <el-form-item label="学号">
                    <span>{{ form.studentNo }}</span>
                </el-form-item>
                <el-form-item label="姓名">
                    <span>{{ form.studentRealName }}</span>
                </el-form-item>
                <el-form-item label="生日">
                    <span>{{ form.studentBirthDate | dateFilter }}</span>
                </el-form-item>
                <el-form-item label="班级">
                    <span>{{ form.studentClassId | classFilter }}</span>
                </el-form-item>
                <el-form-item label="性别">
                    <span>{{ form.studentGender | sexFilter }}</span>
                </el-form-item>
                <el-form-item label="健康状况">
                    <span>{{ form.studentHealthStatus }}</span>
                </el-form-item>
                <el-form-item label="去向">
                    <span>{{ form.studentNativePlace }}</span>
                </el-form-item>
                <el-form-item label="实习报告是否已上传">
                    <span>{{ form.internshipReportPath ? '是' : '否' }}</span>
                </el-form-item>
            </el-form>
        </template>
        <template v-if="item.key == 'gr'">
            <el-form ref="form" :model="form" label-width="120px">
                <template v-for="item in grade">
                    <el-form-item :label="item.courseId | courseFilter" :key="item.courseId" >
                        <span>{{ item.resultScore }}</span>
                    </el-form-item>
                </template>
            </el-form>
        </template>
      </el-tab-pane>
    </el-tabs>
    <el-dialog title="录入档案信息" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="家庭住址">
          <el-input v-model="temp.studentAdress" aria-disabled=""></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="temp.studentPhoneNum" aria-disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="政治面貌">
          <el-input v-model="temp.politicalStatus" aria-disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findStudentInfoDetail, saveStudentExtendInfo } from '@/api/student'
import { getToken } from '@/utils/auth'
export default {
  name: 'tab',
  data() {
    return {
      tabMapOptions: [
        { label: '学生信息', key: 'in' },
        { label: '学生成绩', key: 'gr' }
      ],
      activeName: 'in',
      form: {},
      grade: {},
      fileList3:[],
      action: '',
      dialogFormVisible: false,
      temp: {
        studentId: '',
        studentAdress: '',
        studentPhoneNum: '',
        politicalStatus: ''
      }
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
        courseFilter(courseid) {
            const courseMap = {
                1: 'Web程序设计',
                2: '大学生就业指导',
                3: '分布式数据库应用',
                4: '数据结构课程设计'
            }
            return courseMap[courseid]
        },
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
        dateFilter(datas) {
            var date = new Date(datas)
            var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
            return dateInfo
        }
  },
  created() {
      this.fetchData()
  },
  methods: {
    fetchData() {
        const tempData = {
            userId: getToken()
        }
        findStudentInfoDetail(tempData).then(response => {
            this.form = Object.assign({}, response.data.studentInfo)
            this.grade = Object.assign({}, response.data.resultInfo)
            this.action = '/studentInfo/uploadFile?studentId=' + response.data.studentInfo.id + '&studentNo=' + response.data.studentInfo.studentNo
        })
    },
    showCreatedTimes() {
      this.createdTimes = this.createdTimes + 1
    },
    handleChange(file, fileList) {

    },
    updateData() {
        this.temp.studentId = getToken()
        saveStudentExtendInfo(this.temp).then(response => {
            if(response.success) {
                this.$notify({
                    title: '成功',
                    message: '录入成功',
                    type: 'success',
                    duration: 2000
                })
                this.fetchData()
            } else {
                 this.$notify({
                    title: '录入失败',
                    message: response.message,
                    type: 'error',
                    duration: 2000
                })
            }
            this.dialogFormVisible = false
        })
    },
    handleSuccess(response, file, fileList) {
        if(response.success) {
            this.$notify({
                title: '成功',
                message: '上传成功',
                type: 'success',
                duration: 2000
            })
            this.fetchData()
        } else {
             this.$notify({
                title: '上传失败',
                message: response.message,
                type: 'error',
                duration: 2000
            })
        }
    },
    handleError(err, file, fileList){
        this.$notify({
        title: '上传失败',
        message: '上传实习报告失败',
        type: 'error',
        duration: 2000
        })
        this.fetchData()
    },
  }
}
</script>

<style scoped>
  .tab-container{
    margin: 30px;
  }
.upload-demo {
  padding: 3px;
}
</style>
