import { createApp } from 'vue'
import App from './App.vue'
import VueRouter from './router';
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080"
});

const app =  createApp(App);
app.config.globalProperties.$axios = axiosInstance;
app.config.globalProperties.mode = 'production';
app.use(VueRouter);

app.mount('#app');
