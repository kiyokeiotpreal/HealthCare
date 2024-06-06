<template>
    <div class="mt-3 mb-3 col-xl-8 col-lg-8 col-md-10 col-sm-12 col-12 " style="margin: auto;">
        <div class="flex align-center mt-4 mb-3">
            <router-link to="/">
                <a href="" style="color: black;"><i class="fa-solid fa-house mr-3"> / </i></a>
            </router-link>
            <p class="title mb-0">Lịch hẹn đã đặt</p>
        </div>

            <v-card-subtitle style="font-size: 20px; font-weight: 550;">Lịch hẹn đã đặt</v-card-subtitle>

        <v-card class="mx-auto mt-3" style="border-radius: 20px;" max-width="80%" v-for="(appointment, index) in bookedAppointments" :key="index">
            <div class="flex">
                <div class="left mt-3 mx-3 d-flex flex-column   align-items-center"
                    style="width: 30%; font-size: 20px; ">
                    <vs-avatar :size="90" style="border-radius: 50%;">
                        <img :src="appointment.avata" alt="">
                    </vs-avatar>
                    <div class="mt-3" style="color: rgb(255, 194, 14);">
                        <div class="flex mt-2 mb-2">
                            <i class='bx bx-time-five'></i>
                            <p class="ml-2 mb-0" style="font-size: 18px; ">{{ appointment.time }}</p>
                        </div>
                        <div class="flex mt-2 mb-2">
                            <i class='bx bxs-calendar'></i>
                            <p class="ml-2 mb-0" style="font-size: 18px;">{{ appointment.selectedDay }}</p>
                        </div>
                    </div>

                </div>
                <div class="right" style="width: 70%;">
                    <v-card-text style="font-size: 18px;">
                        <p>Bệnh nhân: {{ appointment.namePatient }}</p>
                        <p>Bác sĩ: {{ appointment.position }}{{ appointment.name }}</p>
                        <p v-if="appointment.nameClinic !== 'TƯ VẤN QUA VIDEO'">Nơi khám: {{ appointment.nameClinic }}
                        </p>
                        <p v-if="appointment.nameClinic === 'TƯ VẤN QUA VIDEO'">Hình thức khám: {{
                            appointment.nameClinic }}</p>
                        <p v-if="appointment.addressClinic !== null">Địa chỉ: {{ appointment.addressClinic }}</p>
                        <p>Giá khám: {{ appointment.price }}</p>
                        <div
                            style="width: 150px; padding: 7px; margin-top: 10px; border-radius: 10px; text-align: center; background-color: rgb(255, 194, 14);">
                            <span class=" text-white">Đã đặt khám</span>
                        </div>
                    </v-card-text>
                </div>
            </div>
        </v-card>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// Lấy dữ liệu từ LocalStorage
const bookedAppointments = ref(JSON.parse(localStorage.getItem('bookedAppointments')) || []);
</script>

<style></style>