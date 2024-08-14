<script setup>
import { ref } from "vue";
const Pwd = ref({
  old_pwd: "",
  new_pwd: "",
  re_pwd: "",
});

// 校验密码函数
const checkRePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次确认密码"));
  } else if (value !== Pwd.value.new_pwd) {
    callback(new Error("请确认两次输入的密码一样"));
  } else {
    callback();
  }
};

const rules = {
  old_pwd: [
    { required: true, message: "请输入新密码", trigger: "blur" },
    { min: 5, max: 16, message: "长度为5~16位非空字符", trigger: "blur" },
  ],
  new_pwd: [
    { required: true, message: "请输入新密码", trigger: "blur" },
    { min: 5, max: 16, message: "长度为5~16位非空字符", trigger: "blur" },
  ],
  re_pwd: [{ validator: checkRePassword, trigger: "blur" }],
};

const clearPwdData = () => {
  Pwd.value.new_pwd = "";
  Pwd.value.old_pwd = "";
  Pwd.value.re_pwd = "";
};

import { ElMessage } from "element-plus";
import { resetPassword } from "@/api/user.js";
import useUserInfoStore from "@/stores/userInfo.js";
const userInfoStore = useUserInfoStore();
const userResetPassword = async () => {
  let result = await resetPassword(Pwd.value);
  ElMessage.success("修改成功");
  clearPwdData();

  // 修改Pinia中的个人信息
  userInfoStore.info.password = Pwd.value.new_pwd;
};
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>重置密码</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-form :model="Pwd" :rules="rules" label-width="110px" size="large">
          <el-form-item
            label="原密码"
            prop="old_pwd"
            :prefix-icon="Lock"
            type="password"
          >
            <el-input v-model="Pwd.old_pwd"></el-input>
          </el-form-item>
          <el-form-item
            label="新密码"
            prop="new_pwd"
            :prefix-icon="Lock"
            type="password"
          >
            <el-input v-model="Pwd.new_pwd"></el-input>
          </el-form-item>
          <el-form-item
            label="再次输入新密码"
            prop="re_pwd"
            :prefix-icon="Lock"
            type="password"
          >
            <el-input v-model="Pwd.re_pwd"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="userResetPassword"
              >提交修改</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>
