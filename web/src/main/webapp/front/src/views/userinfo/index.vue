<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="真实姓名">
        <span>{{ form.userRealName }}</span>
      </el-form-item>
      <el-form-item label="电话">
        <span>{{ form.userPhoneNum }}</span>
      </el-form-item>
      <el-form-item label="邮箱">
        <span>{{ form.userEmail }}</span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onUpdate">修改</el-button>
        <el-button @click="onCancel">返回首页</el-button>
      </el-form-item>
    </el-form>


    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="用户名" prop="userRealName">
          <el-input v-model="temp.userRealName"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="userPhoneNum">
          <el-input v-model="temp.userPhoneNum"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="temp.userEmail"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">提交</el-button>
        <el-button v-else type="primary" @click="updateData">修改</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { findUserExtendInfo, saveUserExtendInfo } from '@/api/usermanage'
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      form: {
        id: null,
        userId: null,
        userRealName: '',
        userPhoneNum: '',
        userEmail: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '添加'
      },
      temp: {
        id: null,
        userId: null,
        userRealName: '',
        userPhoneNum: '',
        userEmail: ''
      },
      dialogPvVisible: false,
      rules: {
        userRealName: [{ required: true, message: '姓名不能为空', trigger: 'change' }],
        userPhoneNum: [{ required: true, message: '电话不能为空', trigger: 'change' }]
      }
    }
  },
  created() {
      this.fetchData()
  },
  methods: {
    fetchData() {
      const tempData = { userId: getToken('userid') }
      findUserExtendInfo(tempData).then(response => {
          this.form = Object.assign({}, response.data.userExtendInfoVO)
      }) 
    },
    onUpdate() {
        this.temp = Object.assign({}, this.form)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
            this.$refs['dataForm'].clearValidate()
        })
    },
    updateData() {
        this.$refs['dataForm'].validate((valid) => {
            if(valid) {
                const tempData = {
                    userId: getToken(),
                    userRealName: this.temp.userRealName,
                    userPhoneNum: this.temp.userPhoneNum,
                    userEmail: this.temp.userEmail,
                    type: 2
                }
                saveUserExtendInfo(tempData).then(response => {
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
                        message: '修改成功',
                        type: 'success',
                        duration: 2000
                    })
                    this.fetchData()
                })
            }
        })
    },
    onCancel() {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

