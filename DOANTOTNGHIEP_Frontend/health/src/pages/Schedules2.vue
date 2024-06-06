<template>
  <div>
    <div class="row mt-2" style="width: 80%; margin: auto; background: white;">
      <div class="flex align-center mt-4 mb-3">
        <router-link to="/">
          <a href="" style="color: black;"><i class="fa-solid fa-house mr-3"> / </i></a>
        </router-link>
        <p class="title mb-0">{{ listSche.serviceTypeName }} / {{ listSche.shortenedName }}</p>
      </div>
      <p style="font-weight: 550 !important; font-size: 20px;">{{ examName }}</p>
    </div>

    <div class="selectSchedule mt-3" style="background: rgba(0, 0, 0, 0.05);">
      <div style="height: 10px;"></div>
      <div class="mt-3" style="width: 80%; margin: auto;">
        <div class="row mt-3" style="border: 1px solid rgba(0, 0, 0, 0.1); border-radius: 10px; background: white;">
          <div class="flex col-md-6 col-12 mt-3 mb-3" style="border-right: 1px solid whitesmoke;">
            <div class="col-lg-3 col-md-4 col-3">
              <a href="" style="text-decoration: none; color: darkblue;">
                <img :src="listSche.avata" alt="" style="height: 150px; width: 150px; margin: auto; border-radius: 50%;">
                <p class="mt-3" style="font-size: 16px; text-align: center;">Xem thêm</p>
              </a>
            </div>
            <div style="font-size: 14px; margin-left: 2%; margin-right: 3%;">
              <p style="font-size: 18px; color: rgb(69 195 210/ 1) !important; font-weight: 600;">{{ listSche.position }} <span>{{ listSche.name }}</span></p>
              <template v-for="(sentence, index) in getDescriptionSentences(listSche.description)" :key="index">
                <p style="margin-bottom: 3px;">{{ sentence }}</p>
              </template>
            </div>
          </div>
          <div class="col-md-6 col-12 mt-3 mb-3">
            <div class="ml-10 mr-10">
              <select v-model="selectedDay" class="custom-select" style="font-size: 16px; color: rgb(51 122 183/ 1); font-weight: 500 !important;">
                <option v-for="day in daysOfWeek" :value="day" :key="day">{{ day }}</option>
              </select>
              <ul class="row mt-2 mb-2">
                <li class="schedule-item col-xl-3 col-lg-4 col-md-6 col-sm-4 col-6" v-for="schedule in filteredSchedules" :key="schedule.time" @click="handleScheduleClick(listSche, schedule)">
                  <p class="mb-0" style="font-size: 14px; font-weight: 550 !important ;"> {{ schedule.time }}</p>
                </li>
              </ul>
            </div>

            <div class="scheduleInfor" style="font-size: 14px;">
              <div class="ml-10 mr-10" style="border-bottom: 1px solid #ced4da;">
                <p style="margin-bottom: 5px !important;">Chọn và đặt (Phí đặt lịch 0đ)</p>
              </div>

              <div class="mt-2 ml-10 mr-10" style="border-bottom: 1px solid #ced4da;">
                ĐỊA CHỈ KHÁM 
                <p style="margin-bottom: 0; font-weight: 550;">{{ listSche.clinicName }}</p>
                <p style="margin-bottom: 5px !important;">{{ listSche.addressClinic }}</p>
              </div>

              <div class="mt-2 ml-10 mr-10">
                GIÁ KHÁM: 
                <span style="margin-bottom: 0; font-weight: 550;">{{ formattedPrice }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div style="height: 20px;"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const router = useRouter()
const store = useStore()

const listSche = computed(() => store.state.listSche);

const getDescriptionSentences = (description) => {
  if (!description || description.trim() === '') {
    return [];
  }
  return description.split('.').filter(sentence => sentence.trim() !== '');
};

const daysOfWeek = computed(() => {
  const uniqueDays = new Set();
  if (listSche.value.scheduleResponses) {
      listSche.value.scheduleResponses.forEach(schedule => {
          uniqueDays.add(schedule.dayofWeek + ' - ' + schedule.date);
      });
  }
  return Array.from(uniqueDays).sort();
});

// Tạo ref cho selectedDay
const selectedDay = ref(daysOfWeek.value[0]);

// Watch sự thay đổi của daysOfWeek
watch(
  () => daysOfWeek.value,
  (newValue, oldValue) => {
    // Nếu selectedDay không nằm trong danh sách các ngày mới, cập nhật selectedDay
    if (!newValue.includes(selectedDay.value)) {
      selectedDay.value = newValue[0];
    }
  }
);

const filteredSchedules = computed(() => {
  if (!listSche.value.scheduleResponses || !selectedDay.value) {
    return [];
  }
  
  return listSche.value.scheduleResponses.filter(schedule => {
    return schedule.dayofWeek + ' - ' + schedule.date === selectedDay.value;
  });
});

// Computed property để định dạng giá
const formattedPrice = computed(() => {
  const price = listSche.value.price;
  if (price == null || isNaN(price)) {
    return "Chưa xác định"; // hoặc giá trị mặc định bạn muốn
  }
  return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + ".000đ";
});

const handleScheduleClick = async (item, schedule) => {
  const scheduleTime = {
    time: schedule.time,
    addressClinic: schedule.addressClinic,
    selectedDay: selectedDay.value,
    avata: item.avata,
    name: item.name,
    position: item.position,
    price: formattedPrice.value
  };
  console.log(scheduleTime)
  store.dispatch('saveScheduleTime', scheduleTime);
  router.push("/datlich");
}

</script>


<style scoped>
.row {
  --bs-gutter-x: 0;
}

.custom-select {
  border-bottom: 1px solid #ced4da;
  padding: 4px;
  background-color: #fff;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 8 4'%3E%3Cpath fill='%23000' d='M0 0l4 4 4-4H0z'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: right 8px center;
  background-size: 8px 4px;
  padding-right: 24px;
}

.schedule-item {
  border: 3px solid white;
  transition: border-color 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.05);
  height: 50px !important;
}

.schedule-item:hover {
  border: 3px solid #007bff;
}

.selectSchedule:hover {
  cursor: pointer;
}
</style>
