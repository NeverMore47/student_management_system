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
  </div>
</template>

<script>
import { findStudentInfoDetail } from '@/api/student'
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
      action: ''
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
                1: '操作系统',
                2: '编译原理',
                3: '数据结构与算法',
                4: '人工智能导论'
            }
            return courseMap[courseid]
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
            console.log(tempData)
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
