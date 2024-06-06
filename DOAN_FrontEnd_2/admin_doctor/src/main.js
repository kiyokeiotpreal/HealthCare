import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import "./assets/style.css"

import Vuesax from 'vuesax-alpha'

import 'vuesax-alpha/theme-chalk/index.css'
// dark mode
import 'vuesax-alpha/theme-chalk/dark/css-vars.css'

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';

import 'boxicons'

import 'boxicons/css/boxicons.min.css';


// import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'



import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import '@fortawesome/fontawesome-free/css/all.css';
import '@fortawesome/fontawesome-free/css/all.css';
import { library } from '@fortawesome/fontawesome-svg-core'
import { faFacebook, faYoutube, faTiktok, faInstagram } from '@fortawesome/free-brands-svg-icons'

import axios from 'axios'

window.axios = axios

library.add(faFacebook, faYoutube, faTiktok, faInstagram)


loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .use(Vuesax)
  .use(Antd)
  // .use(BootstrapVue)
  // .use(IconsPlugin)
  .component('font-awesome-icon', FontAwesomeIcon)
  .mount('#app')
