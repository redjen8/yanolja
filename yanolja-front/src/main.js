import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootStrapVue from 'bootstrap-vue'

import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap/dist/css/bootstrap.css'

Vue.config.productionTip = false

Vue.use(BootStrapVue)

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  render: h => h(App)
}).$mount('#app')

