<template>
  <div>
    <div>
        <navbar/>
    </div>
    <div>
        <router-view/>
            <!-- <Schedule/> -->
    </div>
    <div class="mb-0">
        <navFooter/>
    </div>
  </div>
</template>

<script setup>
import navFooter from '@/components/navFooter';
import navbar from '@/components/navbar';
import Schedule from'@/pages/Schedule.vue'
</script>

<style>

</style>