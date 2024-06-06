
import Booking from '@/pages/Booking.vue'
import Booked from '@/pages/Booked.vue'
import Schedule from '@/pages/Schedule.vue'
import Search from '@/pages/Search.vue'
import Schedules2 from '@/pages/Schedules2.vue'
import Services1 from '@/pages/Services1.vue'
import Services2 from '@/pages/Services2.vue'
import Home from '@/views/Home.vue'
import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    component: Home
  },
  {
    path: '/lichhen',
    component: Booked,
    meta: { scrollToTop: true },
  },
  {
    path: '/timkiem',
    component: Search,
    meta: { scrollToTop: true },
  },
  {
    path: '/dichvukham/:serviceName',
    component: Services1,
    meta: { scrollToTop: true },
  },
  {
    path: '/dichvukham-s2/:serviceName',
    component: Services2,
    meta: { scrollToTop: true },
  },
  {
    path: '/lichtrinh',
    component: Schedule,
    meta: { scrollToTop: true }
  },
  {
    path: '/lichtrinh-s2',
    component: Schedules2,
    meta: { scrollToTop: true }
  },

  {
    path: '/datlich',
    component: Booking,
    meta: { scrollToTop: true }
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
