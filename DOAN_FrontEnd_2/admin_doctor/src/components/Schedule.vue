<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-spacer></v-spacer>

      <div class="center">
        <vs-button style="height: 40px;" @click="addSchedule = !addSchedule">
          Thêm lịch trình
        </vs-button>
        <vs-dialog width="500px" v-model="addSchedule">
          <template #header>
            <h4 class="not-margin">
              Nhập thông tin lịch trình
            </h4>
          </template>

          <div class="center content-inputs">
            <vs-input type="date" v-model="addScheduleInput.date" label="Date" />
            <vs-input type="number" v-model="addScheduleInput.clinicId" label="Phòng khám"
              placeholder="Nhập mã phòng khám" />
          </div>

          <div class="center content-inputs">
            <vs-input type="time" v-model="addScheduleInput.startTime" label="Start" />
            <vs-input type="time" v-model="addScheduleInput.endTime" label="End" />
          </div>

          <div class="center content-inputs">

          </div>

          <template #footer>
            <div class="footer-dialog">
              <vs-button block @click="addScheduleItem">
                Thêm
              </vs-button>
            </div>
          </template>
        </vs-dialog>
      </div>
      <v-text-field v-model="search" density="compact" label="Search" prepend-inner-icon="mdi-magnify"
        variant="solo-filled" flat hide-details single-line></v-text-field>
    </v-card-title>

    <v-divider></v-divider>
    <v-data-table v-model:search="search" :items="listSchedule" hover :headers="headerSchedule" :items-per-page-options="[
    { value: 5, title: '05' },
    { value: 10, title: '10' },
    { value: 15, title: '15' },
    { value: 20, title: '20' }
    ]"
>
      <template v-slot:item.date="{ item }">
        <span style="display: inline-block; min-width: 100px;">{{ item.date }}</span>
      </template>
      <template v-slot:item.time="{ item }">
        <span style="display: inline-block; min-width: 100px;">{{ item.time }}</span>
      </template>
      <template v-slot:item.emailPatient="{ item }">
        <span style="display: inline-block; max-width: 100px;">{{ item.emailPatient }}</span>
      </template>
      <template v-slot:item.namePatient="{ item }">
        <span style="display: inline-block; min-width: 140px;">{{ item.namePatient }}</span>
      </template>
      <template v-slot:item.yearPatient="{ item }">
        <span style="display: inline-block; min-width: 90px;">{{ item.yearPatient }}</span>
      </template>
      <template v-slot:item.genderPatient="{ item }">
        <span style="display: inline-block; min-width: 80px;">{{ item.genderPatient }}</span>
      </template>
      <template v-slot:item.phonePatient="{ item }">
        <span style="display: inline-block; min-width: 120px;">{{ item.phonePatient }}</span>
      </template>
      <template v-slot:item.reason="{ item }">
        <span style="display: inline-block; min-width: 150px;">{{ item.reason }}</span>
      </template>
      <template v-slot:item.medicalHistoryPatient="{ item }">
        <span style="display: inline-block; min-width: 150px;">{{ item.medicalHistoryPatient }}</span>
      </template>
      <template v-slot:item.empty="{ item }">
        <span style="display: inline-block; min-width: 100px;">{{ item.empty == true ? 'Trống' : 'Đã đặt' }}</span>
      </template>

      <template v-slot:item.action="{ item }">
        <div style="min-width: 140px; max-width: 150px; display: flex; align-items: center;" class="my-2" rounded>
          <div class="center">
            <vs-button style="background-color: yellow; color: black;" @click="openResetDiaglog(item.id)"  v-if="!item.empty">
              Reset</vs-button>
            <vs-dialog width="550px" not-center v-model="resetScheduleDialog">
              <template #header>
                <h4 class="not-margin">
                  Bạn có chắc chắn muốn đặt lại lịch trình ?
                </h4>
              </template>

              <template #footer>
                <div class="con-footer">
                  <vs-button @click="resetSchedule" transparent>
                    Ok
                  </vs-button>
                  <vs-button @click="resetScheduleDialog = false" dark transparent>
                    Cancel
                  </vs-button>
                </div>
              </template>
            </vs-dialog>
          </div>
          <div class="center">
            <vs-button class="bg-red" flat @click="openDeleteDialog(item.id)" v-if="item.empty">
              Xoá
            </vs-button>
            <vs-dialog width="550px" not-center v-model="deleteScheduleDialog">
              <template #header>
                <h4 class="not-margin">
                  Bạn có chắc chắn muốn xoá lịch trình ?
                </h4>
              </template>

              <template #footer>
                <div class="con-footer">
                  <vs-button @click="deleteSchedule" transparent>
                    Ok
                  </vs-button>
                  <vs-button @click="deleteScheduleDialog = false" dark transparent>
                    Cancel
                  </vs-button>
                </div>
              </template>
            </vs-dialog>
          </div>
        </div>
      </template>
    </v-data-table>
  </v-card>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useStore } from 'vuex';
const store = useStore()

// Khai báo biến listSchedule
const listSchedule = ref([]);

const search = ref('')

const email = computed(() => JSON.parse(localStorage.getItem('user')).userName);
// Lấy token từ localStorage
const token = JSON.parse(localStorage.getItem('user')).token;

const fetchSchedules = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8088/api/v1/user/getScheduleByDoctor?email=${email.value}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    const { scheduleResponses } = response.data;
    listSchedule.value = scheduleResponses;
    console.log(listSchedule.value);

  } catch (error) {
    console.error("Lỗi khi lấy lịch trình:", error);
  }
};


onMounted(async () => {
  await fetchSchedules();
});




const addSchedule = ref(false);

const addScheduleDialog = ref(false);
const editScheduleDialog = ref(false);
const deleteScheduleDialog = ref(false);
const resetScheduleDialog = ref(false)

const currentScheduleId = ref(null);

const headerSchedule = ref([
  { title: 'Thời gian', key: 'time', align: 'center', },
  { title: 'Ngày', key: 'date', align: 'center', },
  { title: 'Bệnh nhân', key: 'namePatient', align: 'center', },
  { title: 'Năm sinh', key: 'yearPatient', align: 'center', },
  { title: 'Giới tính', key: 'genderPatient', align: 'center', },
  { title: 'Số điện thoại', key: 'phonePatient', align: 'center', },
  { title: 'Email', align: 'center', key: 'emailPatient', sortable: false, class: 'bold-title', },
  { title: 'Lý do khám', key: 'reason', align: 'center', },
  { title: 'Lịch sử khám', key: 'medicalHistoryPatient', align: 'center', },
  { title: 'Trạng thái', key: 'empty', align: 'center', },
  { title: '', key: 'action', align: 'center', },
])

// Khai báo biến addScheduleRequest
const addScheduleInput = ref({
  date: '',
  startTime: '',
  endTime: '',
  clinicId: ''
});

// Hàm xử lý khi click vào nút "Thêm"
const addScheduleItem = async () => {
  // Gán giá trị từ các ô input vào biến addScheduleRequest
  try {
    const addScheduleRequest = {
      emailDoctor: email.value,
      date: addScheduleInput.value.date,
      startTime: addScheduleInput.value.startTime,
      endTime: addScheduleInput.value.endTime,
      clinicId: addScheduleInput.value.clinicId
    }
    console.log(addScheduleRequest)
    const response = await axios.post(
      `http://localhost:8088/api/v1/user/addSchedule`, addScheduleRequest,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    alert("Thêm lịch trình thành công")
    await fetchSchedules();

  } catch (error) {
    if (error.response.status == "400") {
      alert("Ngày hoặc thời gian không phù hợp")
    }
    if (error.response.status == "404") {
      alert("Mã phòng khám không đúng !")
    }
    if (error.response.status == "500") {
      alert("Lỗi kết nối")
    }
  }

};

const openResetDiaglog = (id) => {
  currentScheduleId.value = id;
  resetScheduleDialog.value = true;
}
const openDeleteDialog = (id) => {
  currentScheduleId.value = id;
  deleteScheduleDialog.value = true;
}

const resetSchedule = async () => {
  try {
    // Lấy token từ localStorage
    const token = JSON.parse(localStorage.getItem('user')).token;

    // Thêm token vào header của request
    const response = await axios.put(
      `http://localhost:8088/api/v1/user/resetSchedule?id=${currentScheduleId.value}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    alert("Reset lịch trình thành công");
    resetScheduleDialog.value = false
    // Tải lại danh sách lịch trình mà không cần reload trang
    await fetchSchedules();

  } catch (error) {
    if (error.response.status == 500) {
      alert("Lỗi kết nối");
    }
  }
};



const deleteSchedule = async () => {
  try {
    // Lấy token từ localStorage
    const token = JSON.parse(localStorage.getItem('user')).token;
    console.log(currentScheduleId.value)

    // Thêm token vào header của request
    const response = await axios.delete(
      `http://localhost:8088/api/v1/user/deleteSchedule?id=${currentScheduleId.value}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    alert("Xoá lịch trình thành công")
    deleteScheduleDialog.value = false
    // Tải lại danh sách lịch trình mà không cần reload trang
    await fetchSchedules();

  } catch (error) {
    if (error.response.status == "404") {
      alert("Lịch trình đã bị xoá trước đó")
    }
    if (error.response.status == "500") {
      alert("Lỗi kết nối")
    }
  }
}
</script>

<style></style>