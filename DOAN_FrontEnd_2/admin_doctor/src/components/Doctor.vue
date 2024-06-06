<template>
  <div>
    <v-card flat>
      <v-card-title class="d-flex align-center pe-2">
        <v-spacer></v-spacer>
        <!-- <v-text-field v-model="search" density="compact" label="Search" prepend-inner-icon="mdi-magnify"
          variant="solo-filled" flat hide-details single-line></v-text-field> -->
        <div class="center">
          <vs-button style="height: 40px;" @click="addDoctorDialog = !addDoctorDialog">
            Thêm bác sĩ
          </vs-button>
          <vs-dialog width="500px" v-model="addDoctorDialog">
            <template #header>
              <h4 class="not-margin">
                Nhập thông tin bác sĩ
              </h4>
            </template>

            <div class="center content-inputs">
              <vs-input v-model="addDoctor.avata" placeholder="Avata">
                <template #icon>
                  <i class='bx bxs-user-circle'></i>
                </template>
              </vs-input>
              <vs-input v-model="addDoctor.name" placeholder="Họ tên">
                <template #icon>
                  <i class='bx bx-user'></i>
                </template>
              </vs-input>

            </div>

            <div class="center content-inputs">
              <vs-input v-model="addDoctor.position" placeholder="Vị trí">
                <template #icon>
                  <i class='bx bxs-select-multiple'></i>
                </template>
              </vs-input>
              <vs-input v-model="addDoctor.specialistName" placeholder="Chuyên khoa">
                <template #icon>
                  <box-icon name='building-house'></box-icon>
                </template>
              </vs-input>

            </div>

            <div class="center content-inputs">
              <div style="display: flex; width: 220px; font-size: 13px;">
                <vs-radio v-model="addDoctor.gender" value="male">
                  Nam
                </vs-radio>
                <vs-radio class="ml-3" v-model="addDoctor.gender" value="female">
                  Nữ
                </vs-radio>
              </div>
              <vs-input type="number" v-model="addDoctor.phone" placeholder="Điện thoại">
                <template #icon>
                  <i class='bx bx-phone'></i>
                </template>
              </vs-input>
            </div>


            <div class="center content-inputs">
              <vs-input v-model="addDoctor.email" placeholder="Email">
                <template #icon>
                  <i class='bx bxs-envelope'></i>
                </template>
              </vs-input>
              <vs-input v-model="addDoctor.password" placeholder="Mật khẩu">
                <template #icon>
                  <i class='bx bx-lock-open-alt'></i>
                </template>
              </vs-input>
            </div>
            <div class=" content-inputs">
              <vs-input v-model="addDoctor.price" placeholder="Giá khám">
                <template #icon>
                  <i class='bx bx-lock-open-alt'></i>
                </template>
              </vs-input>
            </div>


            <div class="center content-inputs">
              <textarea class="custom-textarea" v-model="addDoctor.description" placeholder="Mô tả"
                style="width: 440px; height: 100px;"></textarea>
            </div>

            <template #footer>
              <div class="footer-dialog">
                <vs-button block @click="handleAddDoctor">
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
      <v-data-table v-model:search="search" :items="listDoctorFormat" hover :headers="headerDoctor">
        <template v-slot:item.avata="{ item }">
          <v-card style="min-width: 70px; max-width: 80px;" class="my-2" rounded="xl">
            <v-img style="width: 100%;height: 100%;" :src="item.avata" height="64" cover></v-img>
          </v-card>
        </template>
        <template v-slot:item.position="{ item }">
          <div style="min-width: 60px; max-width: 80px" class="my-2" rounded>
            <p style="width: 100%;height: 100%;" height="64" cover>{{ item.position }}</p>
          </div>
        </template>
        <template v-slot:item.name="{ item }">
          <div style="min-width: 80px; max-width: 100px" class="my-2" rounded>
            <p style="width: 100%;height: 100%;" height="64" cover>{{ item.name }}</p>
          </div>
        </template>
        <template v-slot:item.specialistName="{ item }">
          <div style="min-width: 100px; max-width: 120px" class="my-2" rounded>
            <p style="width: 100%;height: 100%;" height="64" cover>{{ item.specialistName }}</p>
          </div>
        </template>
        <template v-slot:item.email="{ item }">
          <div style="min-width: 80px; max-width: 80px" class="my-2" rounded>
            <p style="width: 100%;height: 100%;" height="" cover>{{ item.email }}</p>
          </div>
        </template>
        <template v-slot:item.description="{ item }">
          <div style="min-width: 250px; max-width: 250px;" class="my-2" rounded>
            <p style="width: 100%;height: 100%;" height="64" cover>{{ item.description }}</p>
          </div>
        </template>
        <template v-slot:item.price="{ item }">
          <div style="min-width: 120px; max-width: 150px;" class="my-2" rounded>
            <p style="width: 100%;height: 100%;" height="64" cover>{{ item.price }}.000</p>
          </div>
        </template>
        <template v-slot:item.action="{ item }">
          <div style="min-width: 130px; max-width: 130px; display: flex; align-items: center;" class="my-2" rounded>
            <vs-button @click="openEditDialog(item)" style="width: 40%;">Sửa</vs-button>
            <div class="center">
              <vs-dialog width="500px" v-model="editDoctorDialog">
                <template #header>
                  <h4 class="not-margin">
                    Sửa thông tin bác sĩ
                  </h4>
                </template>

                <div class="center content-inputs">
                  <vs-input v-model="editingDoctor.avata" placeholder="Avata">
                    <template #icon>
                      <i class='bx bxs-user-circle'></i>
                    </template>
                  </vs-input>
                  <vs-input v-model="editingDoctor.name" placeholder="Họ tên">
                    <template #icon>
                      <i class='bx bx-user'></i>
                    </template>
                  </vs-input>

                </div>

                <div class="center content-inputs">
                  <vs-input v-model="editingDoctor.position" placeholder="Vị trí">
                    <template #icon>
                      <i class='bx bxs-select-multiple'></i>
                    </template>
                  </vs-input>
                  <vs-input v-model="editingDoctor.specialistName" placeholder="Chuyên khoa">
                    <template #icon>
                      <box-icon name='building-house'></box-icon>
                    </template>
                  </vs-input>

                </div>

                <div class="center content-inputs">
                  <div style="display: flex; width: 220px; font-size: 13px;">
                    <vs-radio v-model="editingDoctor.gender" value="Nam">
                      Nam
                    </vs-radio>
                    <vs-radio class="ml-3" v-model="editingDoctor.gender" value="Nữ">
                      Nữ
                    </vs-radio>
                  </div>
                  <vs-input type="number" v-model="editingDoctor.phone" placeholder="Điện thoại">
                    <template #icon>
                      <i class='bx bx-phone'></i>
                    </template>
                  </vs-input>
                </div>


                <div class="center content-inputs">
                  <vs-input v-model="editingDoctor.email" placeholder="Email">
                    <template #icon>
                      <i class='bx bxs-envelope'></i>
                    </template>
                  </vs-input>
                  <vs-input v-model="editingDoctor.price" placeholder="Giá khám">
                    <template #icon>
                      <i class='bx bx-lock-open-alt'></i>
                    </template>
                  </vs-input>
                </div>


                <div class="center content-inputs">
                  <textarea class="custom-textarea" v-model="editingDoctor.description" placeholder="Mô tả"
                    style="width: 440px; height: 100px;"></textarea>
                </div>

                <template #footer>
                  <div class="footer-dialog">
                    <vs-button block @click="handleUpdateDoctor">
                      Cập nhật
                    </vs-button>
                  </div>
                </template>
              </vs-dialog>
            </div>
            <div class="center">
              <vs-button flat @click="confirmDelete = !confirmDelete">
                Xoá
              </vs-button>
              <vs-dialog width="550px" not-center v-model="confirmDelete">
                <template #header>
                  <h4 class="not-margin">
                    Bạn có chắc chắn muốn xoá ?
                  </h4>
                </template>

                <div class="con-content">
                  <p>

                  </p>
                </div>

                <template #footer>
                  <div class="con-footer">
                    <vs-button @click="deleteDoctor(item.email)" transparent>
                      Ok
                    </vs-button>
                    <vs-button @click="confirmDelete = false" dark transparent>
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
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useStore } from 'vuex';
const store = useStore()
import { useRouter } from 'vue-router';
const router = useRouter()

// Khi component được mounted, tự động gọi action getDataDoctor
onMounted(async () => {
  await store.dispatch('getDataDoctor');
  listDoctorFormat.value = listDoctor.value;
});
const listDoctor = computed(() => {
  return store.state.listDoctor
})


const search = ref("")
const listDoctorFormat = ref([])
watch(() => search.value, (newValue, oldValue) => {
  const searchValue = newValue.toLowerCase()
  listDoctorFormat.value = listDoctor.value.filter(doctor => {
    const doctorName = doctor.name.toLowerCase()
    const specialistName = doctor.specialistName.toLowerCase()
    return doctorName.includes(searchValue) || specialistName.includes(searchValue)
  })
})


const headerDoctor = ref([
  { title: 'Hình ảnh', key: 'avata', align: 'center', },
  { title: 'Vị trí', key: 'position', align: 'center', },
  { title: 'Tên', align: 'center', key: 'name', sortable: false, class: 'bold-title', },
  { title: 'Chuyên khoa', key: 'specialistName', align: 'center', },
  { title: 'Email', key: 'email', align: 'center', },
  { title: 'Mô tả', key: 'description', align: 'center', },
  { title: 'Giá khám (VNĐ)', key: 'price', align: 'center', },
  { title: '', key: 'action', align: 'center', },
])

const addDoctorDialog = ref(false);
const editDoctorDialog = ref(false);
const confirmDelete = ref(false);


const addDoctor = ref({
  avata: '',
  name: '',
  position: '',
  specialistName: '',
  gender: '',
  phone: '',
  email: '',
  password: '',
  description: '',
  price: ''
});
const editingDoctor = ref(null);


const handleAddDoctor = async () => {
  try {
    const doctor = {
      avata: addDoctor.value.avata,
      name: addDoctor.value.name,
      position: addDoctor.value.position,
      specialistName: addDoctor.value.specialistName,
      gender: addDoctor.value.gender,
      phone: addDoctor.value.phone,
      email: addDoctor.value.email,
      password: addDoctor.value.password,
      description: addDoctor.value.description,
      price: addDoctor.value.price
    }
    console.log(doctor)
    // Lấy token từ localStorage
    const token = JSON.parse(localStorage.getItem('user')).token;

    // Thêm token vào header của request
    const response = await axios.post(
      "http://localhost:8088/api/v1/user/createDoctor",
      doctor,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    alert("Thêm bác sĩ thành công")
    addDoctorDialog.value = false;


  } catch (error) {
    if (error.response.status == "400") {
      alert("Bác sĩ đã tồn tại")
    }
    if (error.response.status == "404") {
      alert("Tên chuyên khoa không đúng !")
    }
    if (error.response.status == "500") {
      alert("Lỗi kết nối")
    }
  }
}

const openEditDialog = (doctor) => {
  editingDoctor.value = { ...doctor };
  editDoctorDialog.value = true;
}

const handleUpdateDoctor = async () => {
  // Xử lý lưu thông tin bác sĩ sau khi sửa
}

const deleteDoctor = async (email) => {
  try {
    // Lấy token từ localStorage
    const token = JSON.parse(localStorage.getItem('user')).token;

    // Thêm token vào header của request
    const response = await axios.delete(
      `http://localhost:8088/api/v1/user/deleteDoctor?email=${email}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    alert("Xoá bác sĩ thành công")
    window.location.reload();

  } catch (error) {
    if (error.response.status == "404") {
      alert("Bác sĩ đã bị xoá trước đó")
    }
    if (error.response.status == "500") {
      alert("Lỗi kết nối")
    }
  }
}

</script>

<style>
.content-inputs {
  display: flex;
  margin-bottom: 20px;
  justify-content: space-around;
}

/* Thiết lập margin giữa các vs-input */
.content-inputs .vs-input {
  margin-right: 20px;
  /* Khoảng cách 10px giữa các vs-input */
}

/* Xóa margin bên phải của vs-input cuối cùng */
.content-inputs .vs-input:last-child {
  margin-right: 0;
}

.custom-textarea {
  width: 400px;
  height: 100px;
  border: 1px solid #ccc;
  /* Màu và độ dày của viền */
  border-radius: 10px;
  /* Độ cong của các góc */
  padding: 8px;
  /* Khoảng cách giữa nội dung và viền */
  background-color: #f8f8f8;
  /* Màu nền */
  resize: vertical;
  /* Cho phép thay đổi kích thước dọc */
}

.custom-textarea::placeholder {
  font-size: 13px;
  /* Kích thước chữ của placeholder */
  color: #bcbcbc;
  /* Màu chữ của placeholder */
}
</style>
