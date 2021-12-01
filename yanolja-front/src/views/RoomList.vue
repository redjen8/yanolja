<template>
  <b-container>
    <b-row>
      <h1>RoomList</h1>
    </b-row>
    <b-row align-v="center">
      <b-col sm="4">
        예약 시작 시간 : {{ startDate }} {{ startTime }}
      </b-col>
      <b-col sm="4">
        <b-calendar id="calendarStartDate" v-model="startDate" selected-variant="primary"></b-calendar>
      </b-col>
      <b-col sm="4">
        <b-time id="timePickerStartTime" v-model="startTime"></b-time>
      </b-col>
    </b-row>
    <hr>
    <b-row align-v="center">
      <b-col sm="4">
        예약 종료 시간 : {{ endDate }} {{ endTime }}
      </b-col>
      <b-col sm="4">
        <b-calendar id="calendarEndDate" v-model="endDate" selected-variant="primary"></b-calendar>
      </b-col>
      <b-col sm="4">
        <b-time id="timePickerEndTime" v-model="endTime"></b-time>
      </b-col>
    </b-row>

    <br>
    <b-button id="btnSelectConditionRoom" @click="getRoomListAvailable()">조회</b-button>
  </b-container>
</template>

<script>
import axios from 'axios'

let now = new Date();
const todayDate = now.getFullYear() + "-" + ("00" + (now.getMonth() + 1)).slice(-2) + "-" + ("00" + now.getDate()).slice(-2)
now.setDate(now.getDate() + 1)
const tomorrowDate = now.getFullYear() + "-" + ("00" + (now.getMonth() + 1)).slice(-2) + "-" + ("00" + now.getDate()).slice(-2)

export default {
  name: "RoomList",
  data: () => {
    return {
      startDate: todayDate,
      startTime: '18:00:00',
      endDate: tomorrowDate,
      endTime: '10:00:00',
    }
  },
  methods: {
    getRoomListAvailable() {
      const conditionStart = this.startDate + " " + this.startTime
      const conditionEnd = this.endDate + " " + this.endTime

      const params = new URLSearchParams([[ "conditionStart", conditionStart], ["conditionEnd", conditionEnd]])
      axios
        .get("http://localhost:9000/api/room/search/available", { params
          // headers: {
          //   'Content-Type': 'application/json;charset=UTF-8',
          //   'Access-Control-Allow-Origin': "*",
          // }
        })
        .then(res => {
          alert(JSON.stringify(res.data))
        })
        .catch(err => {
          console.log(err)
        })
    },
  }
}
</script>

<style scoped>

</style>