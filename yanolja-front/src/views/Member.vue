<template>
  <b-container>
    <b-row align-v="center" class="my-0">
      <h2>회원가입</h2>
    </b-row>
    <br>
    <b-row align-v="center" class="my-1">
      <b-col sm="6">
        <label>E-mail: </label>
      </b-col>
      <b-col sm="6">
        <b-form-input id="signupInputEmail" type="email" placeholder="이메일을 입력해주세요."></b-form-input>
      </b-col>
    </b-row>

    <b-row align-v="center" class="my-1">
      <b-col sm="6">
        <label>비밀번호: </label>
      </b-col>
      <b-col sm="6">
        <b-form-input id="signupInputPassword" type="password" placeholder="비밀번호를 입력해주세요."></b-form-input>
      </b-col>
    </b-row>

      <b-button id="btnSignupConfirm" sm="3" @click="postUserSignupData()">확인</b-button>
  </b-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Member.vue',
  data: () => {
    return {
      text: '',
    }
  },
  methods: {
    postUserSignupData() {
      let saveData = {};
      saveData.email = document.getElementById("signupInputEmail").value;
      saveData.password = document.getElementById("signupInputPassword").value;
      console.log(saveData)
      axios
      .post("http://localhost:9000/api/member/signup", JSON.stringify(saveData), {
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Access-Control-Allow-Origin': "*",
        }
      })
      .then(res => {
        const response = res.data
        alert(response.resultCode + " : " + response.resultMsg)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {

  }
}
</script>

<style scoped>

</style>