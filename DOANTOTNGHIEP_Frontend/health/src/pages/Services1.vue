<template>
  <div class="row mt-2" style="width: 80%; margin: auto;">
    <div class="flex align-center mt-4 mb-3">
      <router-link to="/">
        <a href="" style="color: black;"><i class="fa-solid fa-house mr-3"> / </i></a>
      </router-link>
      <p class="title mb-0">{{ serviceName }}</p>
    </div>
    <div v-for="(slide, index) in listExam" :key="index" class="col-xl-3 col-md-4 col-sm-6 col-6 mb-4">
        <a href="" style="text-decoration: none; color: black;" @click.prevent="getDataSchedule(slide.name)">
          <div class="slide-item">
            <img :src="slide.img" alt="Slide Image" style="max-height: 200px; width: 100%;">
            <div class="sub-title mt-3 mb-3">{{ slide.name }}</div>
          </div>
        </a>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import Axios from 'axios';
import { useStore } from 'vuex';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter()

const route = useRoute()

const store = useStore()

store.dispatch("getDataExam", route.params.serviceName)


const serviceName = computed(() => {
  return store.state.serviceName
})

const listExam = computed(() => {
  return store.state.listExam
});



const getDataSchedule = async(speOrExamName) => {
    try {
        store.dispatch("getDataSche", speOrExamName)
        router.push("/lichtrinh", )
  } catch (error) {
    alert("error");
  }
}


</script>

<style scoped>
@media (min-width: 0) {
  .title {
    font-size: 20px !important;
  }
  .sub-title {
    font-size: 13px !important;
  }
}
@media (min-width: 768px) {
  .title {
    font-size: 16px !important;
  }
  .sub-title {
    font-size: 16px !important;
  }
}
.slide-item {
  text-align: center;
  font-weight: 600 !important;
}
.slide-item img {
  max-width: 100%;
  height: auto;
  border: 1px solid rgb(217, 216, 216);
  border-radius: 20px;
}
</style>
