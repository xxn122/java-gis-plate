import './assets/main.css'

import { createApp} from 'vue'

import App from './App.vue'
import router from './router'
import axios from 'axios'
const app = createApp(App)


app.use(router)
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
app.mount('#app')


