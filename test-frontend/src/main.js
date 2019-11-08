import Vue from 'vue'
import App from './App.vue'

import router from './router'

import 'bootstrap/dist/css/bootstrap.css'

import 'jquery/dist/jquery'
import 'popper.js/dist/popper'
import 'bootstrap/dist/js/bootstrap'

import './assets/css/font-awesome-all.css'

Vue.config.productionTip = false

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')