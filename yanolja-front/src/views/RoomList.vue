<template>
  <b-container>
    <b-row>
      <h1>RoomList</h1>
    </b-row>
    <div v-if="!bSearched">
      <b-row align-v="center">
        <b-col sm="4">
          예약 시작 시간 : {{ startDate }} {{ startTime }}
        </b-col>
        <b-col sm="4">
          <b-calendar id="calendarStartDate" v-model="startDate" selected-variant="primary" hide-header></b-calendar>
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
          <b-calendar id="calendarEndDate" v-model="endDate" selected-variant="primary" hide-header></b-calendar>
        </b-col>
        <b-col sm="4">
          <b-time id="timePickerEndTime" v-model="endTime"></b-time>
        </b-col>
      </b-row>
      <br>
      <b-button id="btnSelectConditionRoom" @click="getRoomListAvailable()">조회</b-button>
    </div>
    <div v-if="bSearched">
      <b-table :items="roomSearchResult" :fields="tableFields" head-variant="light" striped responsive="sm"></b-table>
    </div>
  </b-container>
</template>

<script>
import axios from 'axios'

function getTodayDate() {
  return now.getFullYear() + "-" + ("00" + (now.getMonth() + 1)).slice(-2) + "-" + ("00" + now.getDate()).slice(-2)
}

let now = new Date();
const todayDate = getTodayDate()
now.setDate(now.getDate() + 1)
const tomorrowDate = getTodayDate()


export default {
  name: "RoomList",
  data: () => {
    return {
      bSearched: false,
      roomSearchResult: null,
      startDate: todayDate,
      startTime: '18:00:00',
      endDate: tomorrowDate,
      endTime: '10:00:00',
      tableFields: ['roomName', 'roomShortInfo', 'roomRemainCnt', 'maxPersonCnt', 'defaultPersonCnt', 'roomDescription', 'roomCategory', 'roomPrice', 'bSookbak', 'bDaesil'],
    }
  },
  methods: {
    getRoomListAvailable() {
      const conditionStart = this.startDate + " " + this.startTime
      const conditionEnd = this.endDate + " " + this.endTime

      const params = new URLSearchParams([[ "conditionStart", conditionStart], ["conditionEnd", conditionEnd]])
      axios
        .get("http://localhost:9000/api/room/search/available", { params})
        .then(res => {
          this.roomSearchResult = res.data.data
          this.toggleSearched()
        })
        .catch(err => {
          console.log(err)
        })
    },
    toggleSearched() {
      this.bSearched = !this.bSearched
    }
  }
}
</script>

<style scoped>

</style>