<template>
  <div >
    <router-view/>
  </div>
</template>

<script setup>

import { useStore } from 'vuex';
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router';

const store = useStore()
const router = useRouter()

const user = computed(() => JSON.parse(localStorage.getItem('user')));

function checkLogin() {
  const currentUser = user.value; // Lấy giá trị của user từ computed property
  if (!currentUser) {
    router.push('/login');
  } else {
    router.push('/admin');
  }
}

onMounted(() => {
  checkLogin();
});

</script>

<style>

</style>