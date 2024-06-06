<template>
  <v-container fluid style="width: 80%;">
    <div class="row">
      <div class="col-lg-6 d-none d-lg-block">
        <img src="https://thainguyen.gov.vn/image/journal/article?img_id=10234593&t=1714621349790" 
        alt=""data
        style="">
      </div>

      <div class="col-xl-6 col-lg-6 col-md-12">
        <p style="font-weight: 600; font-size: 19px; text-align: center;">ĐĂNG NHẬP HỆ THỐNG HEALTH CARE </p>
        <form @submit.prevent="submit" class="space-y-6" action="#" method="POST" >
          <div>
            <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email đăng nhập</label>
            <div class="mt-2">
              <v-text-field variant="solo" density="compact"
                v-model="userLogin.email" 
                  :rules="emailRules"
                  id="email" 
                  name="email" 
                  type="email" 
                  autocomplete="email" 
                  required="" 
                  class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
            </div>
          </div>

          <div>
            <div class="flex items-center justify-between">
              <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Mật khẩu</label>
              <div class="text-sm">
                    <a href="#" class="font-semibold text-indigo-600 hover:text-indigo-500">Quên mật khẩu?</a>
              </div>
            </div>
            <div class="mt-2">
              <v-text-field variant="solo" density="compact"
                v-model="userLogin.password" 
                  :rules="passwordRules"
                  id="password" 
                  name="password" 
                  type="password" 
                  autocomplete="current-password" 
                  required="" 
                  class=" block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"  />
            </div>
          </div>

          <div>
            <button @click="handleSignIn" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Đăng nhập</button>
          </div>

          <!-- <div class="text-md" >
                <a style="text-decoration: none;" href="/" class="font-semibold text-indigo-600 hover:text-indigo-500">Trở về trang chủ</a>
          </div> -->
        </form>
      </div>
    </div>
  </v-container>
</template>

<script setup>
import { ref, computed } from 'vue';
import axios from 'axios'
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { stringify } from 'postcss';

const router = useRouter()

const store = useStore()

const emailRules = [
    value => {
        if(/^[a-z.-.0-9]+@[a-z.-]+\.[a-z]+$/i.test(value)){
            return true;
        }
        return "email không hợp lệ"
    }
]

const passwordRules = [
    value => {
        if(value.length < 3){
            return "Mật khẩu phải có ít nhất 3 ký tự";
        }
        return true;
    }
]

const userLogin = ref({
  email : '',
  password : '',
})

const handleSignIn = async() => {
  try {
    const user = {
      email : userLogin.value.email,
      password : userLogin.value.password
    }
    const response = await axios.post("http://localhost:8088/api/v1/user/login", user)
    localStorage.setItem('user', JSON.stringify(response.data))
    router.push("/admin")
    
  } catch (error) {
    if(error.response.status == "404"){
        alert("Email không tồn tại")
    }
    if(error.response.status == "401"){
        alert("Sai mật khẩu")
    }
    if(error.response.status == "403"){
        alert("Tài khoản bị vô hiệu hoá")
    }
    if(error.response.status == "500"){
        alert("Lỗi kết nối")
    }
  }
}


</script>

<style>

</style>