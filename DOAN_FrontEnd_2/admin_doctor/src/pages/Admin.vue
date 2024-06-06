<template>
  <div class="grid grid-cols-12 ">
    <div class="hidden md:block  md:col-span-4 xl:col-span-2 h-100">
      <vs-sidebar v-model="active" absolute open>


        <vs-sidebar-item  class="mt-10" id="schedules" @click="showViewSchedule" v-if="role.includes('ROLE_DOCTOR')">
          <template #icon>
            <i class="bx bx-list-ul" />
          </template>
          Quản lý lịch trình
        </vs-sidebar-item>

        <vs-sidebar-item class="mt-10" id="doctors" @click="showDoctors" v-if="role.includes('ROLE_ADMIN')">
          <template #icon>
            <i class="bx bx-user" />
          </template>
          Quản lý bác sĩ
        </vs-sidebar-item>

        <vs-sidebar-item class="" id="change" @click="openChangePasswordDialog">
          <template #icon>
            <i class="bx bxs-lock" />
          </template>
          Đổi mật khẩu
          <vs-dialog width="100px" v-model="changePasswordDialog">
            <template #header>
              <h4 class="not-margin">
                Đổi mật khẩu
              </h4>
            </template>

            <div class="center content-inputs">
              <vs-input v-model="changePassword.currPassword" placeholder="Mật khẩu hiện tại">
                <template #icon>
                  <i class='bx bx-lock-open-alt'></i>
                </template>
              </vs-input>
            </div>
            <div class="center content-inputs">
              <vs-input v-model="changePassword.newPassword" placeholder="Mật khẩu mới">
                <template #icon>
                  <i class='bx bx-lock-alt'></i>
                </template>
              </vs-input>
            </div>

            <div class="center content-inputs">
              <vs-input v-model="changePassword.confirmPassword" placeholder="Nhập lại mật khẩu">
                <template #icon>
                  <i class='bx bxs-lock'></i>
                </template>
              </vs-input>
            </div>
            <template #footer>
              <div class="footer-dialog">
                <vs-button block @click="handleChangePassword">
                  Xác nhận
                </vs-button>
              </div>
            </template>
          </vs-dialog>
        </vs-sidebar-item>

        <vs-sidebar-item id="logout" @click="confirmLogout = !confirmLogout"
          v-if="role.includes('ROLE_ADMIN') || role.includes('ROLE_DOCTOR')">
          <template #icon>
            <i class="bx bx-log-out" />
          </template>
          Đăng xuất
        </vs-sidebar-item>
        <div class="center">
          <vs-dialog width="550px" not-center v-model="confirmLogout">
            <template #header>
              <h4 class="not-margin">
                Bạn có chắc chắn muốn đăng xuất ?
              </h4>
            </template>

            <div class="con-content">
              <p>

              </p>
            </div>

            <template #footer>
              <div class="con-footer">
                <vs-button @click="logout" transparent>
                  Ok
                </vs-button>
                <vs-button @click="confirmLogout = false" dark transparent>
                  Cancel
                </vs-button>
              </div>
            </template>
          </vs-dialog>
        </div>

        <template #footer>
          <vs-row justify="space-between">
            <div v-if="role.includes('ROLE_ADMIN')" style="margin-bottom: 20px;">
              <vs-avatar style="margin: auto;">
                <img
                  src="data:image/png;base64,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"
                  alt="" />
              </vs-avatar>
              <p>Admin</p>
            </div>
            <div v-if="role.includes('ROLE_DOCTOR')" style="margin-bottom: 20px;">
              <vs-avatar style="margin: auto;">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1dpV1xqkfgxcrv0Tg09_hGI8P1PWo-VEYcA&s"
                  alt="" />
              </vs-avatar>
              <p>Doctor</p>
            </div>
          </vs-row>
        </template>
      </vs-sidebar>
    </div>
    <div class="xl:col-span-10 col-span-12 md:col-span-8 overflow-x-auto">

      <div class="ml-10" v-if="isDoctor">
        <Doctor />
      </div>

      <div v-if="isViewSchedule">
        <Schedule />
      </div>
    </div>
  </div>

</template>


<script setup>
import { ref, computed, onMounted } from 'vue'
import Personal from '@/components/Personal.vue'
import Schedule from '@/components/Schedule.vue'
import Doctor from '@/components/Doctor.vue'
import { useStore } from 'vuex';
import { useRouter } from 'vue-router'

const router = useRouter()
const store = useStore()


const role = computed(() => JSON.parse(localStorage.getItem('user')).roles);

const isViewSchedule = ref(false)
const isDoctor = ref(false)
const changePasswordDialog = ref(false)

// const listDoctor = store.state.listDoctor



const showViewSchedule = () => {
  isViewSchedule.value = true;
  // isConfirmSchedule.value = false;
  // isDoctor.value = false
}

const showDoctors = () => {
  isDoctor.value = true
  // isConfirmSchedule.value = false;
  // isViewSchedule.value = false
}

const changePassword = ref({
  currPassword: '',
  newPassword: '',
  confirmPassword: ''
}
)
const emailUser = computed(() => JSON.parse(localStorage.getItem('user')).userName);
const openChangePasswordDialog = () => {
  changePasswordDialog.value = true
}
const handleChangePassword = async() => {
try {
  const changePasswordRequest = {
    email : emailUser.value,
    oldPassword : changePassword.value.currPassword,
    newPassword : changePassword.value.newPassword,
    retypePassword : changePassword.value.confirmPassword
  }
  console.log(changePasswordRequest)
  const response = await axios.put("http://localhost:8088/api/v1/user/changePassword", changePasswordRequest)
  changePasswordDialog.value = false
  alert("Thay đổi mật khẩu thành công")
  
} catch (error) {
    if (error.response.status == "404") {
      alert("Mật khẩu hiện tại không đúng")
    }
    if (error.response.status == "400") {
      alert("Mật khẩu xác nhận không đúng")
    }
    if (error.response.status == "500") {
      alert("Lỗi kết nối")
    }
}

}

// const navigation = [
//   { name: 'Thông tin cá nhân', href: '#', current: false },
//   { name: 'Danh sách bác sĩ', href: '#', current: true },
//   { name: 'Lịch hẹn', href: '#', current: false },
//   // { name: '', href: '#', current: false },
// ]

// const selectedItem = ref(null);

// const handleNavigation = (item) => {
//   selectedItem.value = item.name;
// }

const confirmLogout = ref(false);

const logout = () => {
  router.push('/login')
  localStorage.removeItem('user');
}
</script>

<style>
.con-footer {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.con-footer .vs-button {
  margin: 0px;
}

.con-footer .vs-button__content {
  padding: 10px 30px;
}

.con-footer .vs-button~.vs-button {
  margin-left: 10px;
}

.not-margin {
  margin: 0px;
  font-weight: normal;
  padding: 10px;
  padding-bottom: 0px;
}

.con-content {
  width: 100%;
}

.con-content p {
  font-size: 0.8rem;
  padding: 0px 10px;
}

.con-content .vs-checkbox-label {
  font-size: 0.8rem;
}

.con-content .vs-input-parent {
  width: 100%;
}

.con-content .vs-input-content {
  margin: 10px 0px;
  width: calc(100%);
}

.con-content .vs-input-content .vs-input {
  width: 100%;
}

.footer-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
}

.footer-dialog .new {
  margin: 0px;
  margin-top: 20px;
  padding: 0px;
  font-size: 0.7rem;
}

.footer-dialog .new a {
  color: rgba(var(--vs-primary), 1) !important;
  margin-left: 6px;
}

.footer-dialog .new a:hover {
  text-decoration: underline;
}

.footer-dialog .vs-button {
  margin: 0px;
}
</style>
