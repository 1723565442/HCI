import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from "./router";
import axios from 'axios';
import store from './stores/index'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.config.globalProperties.$axios = axios;

router.beforeEach((to, from, next) => {
  console.log('Before each triggered');
  const isAuthenticated = store.getters.isAuthenticated;
  console.log('Is authenticated:', isAuthenticated);

  if (to.path !== '/Login' && !isAuthenticated && to.path !== '/Registry' && to.path !== '/Home' ) {
    next('/Login');
  } else {
    next();
  }
});


app.use(router)
app.use(store)
app.use(ElementPlus)
app.mount('#app')