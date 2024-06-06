<template>
    <div class="col-xl-6 col-lg-7 col-md-8 col-10" style="margin: auto; min-height: 500px ;">
        <!-- <v-card flat>
            <v-card-title class="d-flex align-center pe-2">
                <v-spacer></v-spacer>

                <v-text-field class="" v-model="search" density="compact" label="Search"
                    prepend-inner-icon="mdi-magnify" variant="solo-filled" flat hide-details single-line></v-text-field>
            </v-card-title>
        </v-card> -->

        <v-text-field 
        style="width: 400px;" 
        class="justify-end pt-7 pb-7 ml-7" 
        v-model="search" density="compact" label="Nhập tên bác sĩ hoặc tên gói khám"
        prepend-inner-icon="mdi-magnify" variant="solo-filled" flat hide-details single-line></v-text-field>

        <!-- Display search results here -->
        <div style="width: 100%; margin: auto;" v-if="search.trim() !== ''">
            <div v-if="searchResults.doctors.length">
                <ul>
                    <p style="font-weight: 600; font-size: 20px;" class="bg-gray-100">Bác sĩ</p>
                    <li v-for="doctor in searchResults.doctors" :key="doctor.slug" class="doctor-item">
                        <router-link :to="`/bacsi/${doctor.email}`">
                            <div class="doctor-info">
                            <img :src="doctor.avata" class="doctor-avatar" alt="Avatar">
                            <div class="doctor-details">
                                <p style="font-weight: 600; margin-bottom: 0;">{{ doctor.position }}{{ doctor.name }}</p>
                                <p style="margin-bottom: 0;">{{ doctor.specialistName }}</p>
                            </div>
                        </div>
                        </router-link>
                    </li>
                </ul>
            </div>
            <div v-if="searchResults.examServices.length">
                <ul>
                    <li v-for="examService in searchResults.examServices" :key="examService.slug"
                        class="exam-service-item">
                        <div v-if="examService.serviceTypeId===1">
                            <template v-if="shouldDisplayTitle(examService, index)">
                            <p style="font-weight: 600; font-size: 20px;" class="bg-gray-100">{{ getExamServiceTitle(examService.serviceTypeId) }}</p>
                        </template>
                            <div class="exam-service-info" @click="getDataSchedule(examService.name)">
                                <img :src="examService.avata" class="exam-service-image" alt="Exam Service Image">
                                <div class="exam-service-details">
                                    <p style="font-weight: 600; margin-bottom: 0;">{{ examService.name }}</p>
                                </div>
                            </div>
                        </div>
                        <div v-if="examService.serviceTypeId===2">
                            <template v-if="shouldDisplayTitle(examService, index)">
                            <p style="font-weight: 600; font-size: 20px;" class="bg-gray-100">{{ getExamServiceTitle(examService.serviceTypeId) }}</p>
                        </template>
                            <div class="exam-service-info" @click="getDataSchedule2(examService.name)">
                                <img :src="examService.avata" class="exam-service-image" alt="Exam Service Image">
                                <div class="exam-service-details">
                                    <p style="font-weight: 600; margin-bottom: 0;">{{ examService.name }}</p>
                                </div>
                            </div>
                        </div>
                        <div v-if="examService.serviceTypeId===3">
                            <template v-if="shouldDisplayTitle(examService, index)">
                            <p style="font-weight: 600; font-size: 20px;" class="bg-gray-100">{{ getExamServiceTitle(examService.serviceTypeId) }}</p>
                        </template>
                            <div class="exam-service-info" @click="getDataSchedule(examService.name)">
                                <img :src="examService.avata" class="exam-service-image" alt="Exam Service Image">
                                <div class="exam-service-details">
                                    <p style="font-weight: 600; margin-bottom: 0;">{{ examService.name }}</p>
                                </div>
                            </div>
                        </div>
                        <div v-if="examService.serviceTypeId===5">
                            <template v-if="shouldDisplayTitle(examService, index)">
                            <p style="font-weight: 600; font-size: 20px;" class="bg-gray-100">{{ getExamServiceTitle(examService.serviceTypeId) }}</p>
                        </template>
                            <div class="exam-service-info" @click="getDataSchedule2(examService.name)">
                                <img :src="examService.avata" class="exam-service-image" alt="Exam Service Image">
                                <div class="exam-service-details">
                                    <p style="font-weight: 600; margin-bottom: 0;">{{ examService.name }}</p>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup>

import { ref, onMounted, computed, watchEffect, watch } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
const router = useRouter()
const store = useStore()
const search = ref('');
const searchResults = ref({ doctors: [], examServices: [] });
let displayedServiceTypes = [];
const searchAPI = async () => {
    try {
        const response = await fetch(`http://localhost:8088/api/v1/user/search?name=${search.value}`);
        const data = await response.json();
        searchResults.value.doctors = data.doctorSearchResponses;
        searchResults.value.examServices = data.examServiceSearchResponses;
        displayedServiceTypes = [];
        console.log(searchResults.value)
    } catch (error) {
        console.error('Error searching:', error);
    }
};

const shouldDisplayTitle = (examService, index) => {
    // Check if the service type has been displayed before
    if (displayedServiceTypes.indexOf(examService.serviceTypeId) === -1) {
        // Add the service type to the displayed types array
        displayedServiceTypes.push(examService.serviceTypeId);
        return true; // Display the title for the first occurrence of each service type
    }
    return false; // Do not display the title for subsequent occurrences of the same service type
};

const getExamServiceTitle = (serviceTypeId) => {
    switch (serviceTypeId) {
        case 1:
            return 'Khám Chuyên khoa';
        case 2:
            return 'Khám tổng quát';
        case 3:
            return 'Khám từ xa';
        case 5:
            return 'Xét nghiệm y học';
        default:
            return '';
    }
};

// Watch for changes in the search query and trigger the search API
watch(search, () => {
    if (search.value.length >= 2) {
        searchAPI();
    }
});

const getDataSchedule = async(speOrExamName) => {
    try {
        store.dispatch("getDataSche", speOrExamName)
        router.push("/lichtrinh", )
  } catch (error) {
    alert("error");
  }
}
const getDataSchedule2 = async(examName) => {
      try {
          store.dispatch("getDataSche2", examName)
          router.push("/lichtrinh-s2", )
    } catch (error) {
      alert("error");
    }
  }
  

</script>

<style scoped>
.doctor-item {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.doctor-avatar {
    width: 80px;
    height: 80px;
    margin-right: 20px;
    border-radius: 50%;
}

.doctor-info {
    display: flex;
    align-items: center;
}

.exam-service-info {
    height: 100px;
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    border-bottom: 1px solid silver;
}
.exam-service-info:hover {
    cursor: pointer;
}

.exam-service-image {
    width: 80px;
    height: 80px;
    margin-right: 20px;
    object-fit: cover;
    border-radius: 50%;
}
</style>