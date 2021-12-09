<template>
  <b-container>
    <b-row align-v="center" class="my-0">
      <h2>로그인</h2>
    </b-row>
    <br>
    <b-row align-v="center" class="my-1">
      <b-col sm="6">
        <label>E-mail: </label>
      </b-col>
      <b-col sm="6">
        <b-form-input id="loginInputEmail" type="email" placeholder="이메일을 입력해주세요."></b-form-input>
      </b-col>
    </b-row>

    <b-row align-v="center" class="my-1">
      <b-col sm="6">
        <label>비밀번호: </label>
      </b-col>
      <b-col sm="6">
        <b-form-input id="loginInputPassword" type="password" placeholder="비밀번호를 입력해주세요."></b-form-input>
      </b-col>
    </b-row>


      <b-button id="btnLoginConfirm" @click="postUserLogin()">확인</b-button>


    <b-row class="my-2" align="start">
        발급된 jwt: <br>{{ jwt }}
    </b-row>

    <b-row class="my-2">
      조회된 memberVO 인덱스: {{ memberIndex }}
    </b-row>
  </b-container>
</template>

<script>
import axios from 'axios'


export default {
  name: "Login",
  data: () => {
    return {
      jwt: '',
      memberIndex: 0
    }
  },
  methods: {
    postUserLogin() {
      let saveData = {};
      saveData.email = document.getElementById("loginInputEmail").value
      saveData.password = document.getElementById("loginInputPassword").value
      console.log(saveData)
      axios
          .post("http://localhost:9000/api/memberVO/login", JSON.stringify(saveData), {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8',
              'Access-Control-Allow-Origin': "*",
            }
          })
          .then(res => {
            const response = res.data
            this.jwt = response.jwtKey
            this.memberIndex = response.memberIdx
            alert("resultCode: " + response.resultCode + ", Message: " + response.resultMsg)
          })
          .catch(err => {
            console.log(err)
          })
    }
  },
}
</script>

<style scoped>

</style>