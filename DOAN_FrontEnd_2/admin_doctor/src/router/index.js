import Admin from '@/pages/Admin.vue'
import Signin from '@/pages/Signin.vue'
import { createRouter, createWebHistory } from 'vue-router'



const routes = [
  {
    path: '/admin',
    component: Admin
  },
  {
    path: '/login',
    component: Signin,
    meta: { scrollToTop: true },
  },
 

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  // Kiểm tra nếu route có metadata để cuộn đến đầu trang
  if (to.meta.scrollToTop) {
    window.scrollTo(0, 0); // Cuộn đến đầu trang
  }
  next(); // Chuyển hướng tiếp theo
});

// router.beforeRouteEnter((to, from) => {
//   if(to.path == "/examinationService"){
//     store.dispatch("getDataExam", serviceName)
//   }
//   next(); // Chuyển hướng tiếp theo
// })


export default router
