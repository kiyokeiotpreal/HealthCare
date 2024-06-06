<template>
  <Disclosure as="nav" class=""
    style="background-color: rgb(237, 255, 250); font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif !important; "
    v-slot="{ open }">


    <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
      <div class="relative flex  items-center justify-between">
        <div class="row flex flex-1 items-center  sm:items-stretch sm:justify-start">

          <div class="flex flex-shrink-0 items-center justify-start col-sm-6 col-xl-3 col-lg-3 col-md-6 ">
            <router-link to="/" style="text-decoration: none; display: flex; color: #49bce2; align-items: center;">
              <img class="h-11 w-auto"
                src="data:image/png;base64,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"
                alt="Your logo" style="border-radius: 100%;" />
              <h2 style="margin-bottom: 0; padding: 30px; padding-left: 10px; font-weight: 550;">HealthCare</h2>
            </router-link>
          </div>

          <div class="col-xl-6 col-lg-6 col-md-8 col-12 row ">
            <div class=" col-xl-8 col-lg-12 col-md-12 " style="margin: auto;">
              <div class="flex space-x-4 ">
                <a v-for="item in navigation" :key="item.name" :href="item.href" @click="chooseType(item)"
                  :class="[item.current ? 'bg-yellow-300 text-white' : 'text-gray-900 hover:bg-yellow-300 hover:text-white', 'rounded-xl px-3 py-2 text-md font-medium']"
                  :aria-current="item.current ? 'page' : undefined"
                  style="text-decoration: none; font-size: 16px; font-family: Montserrat,sans-serif!important;">{{
                    item.name }}</a>
              </div>
            </div>

            <div class="flex ml-6 py-3 col-xl-3 col-lg-6 col-md-5 col-sm-4 col-6 " style="padding: 0;">

              <a @click="clickSearch" v-if="isShowSearch"
                class=" searchIcon h-12 m-2.5 flex flex-row items-center rounded-[25px] border border-[#DDDDDD] bg-white px-2.5"
                style="width:274px; text-decoration: none;"><svg width="16" height="16" fill="black">
                  <path
                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0">
                  </path>
                </svg><span class="css-1jxf684 ml-3 text-[#999999]">Search</span></a>
            </div>

          </div>

          <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5 col-12 flex flex-shrink-0 justify-start items-center ">
            <a href="/lichhen"><img class="h-12 w-auto" src="lichhen.png" alt="Your logo" /></a>

            <div class="support">
              <img @click="support = !support" class="h-12 w-auto" src="hotro.png" alt="Your logo" />
              <vs-dialog v-model="support" width="550px" not-center>
                <template #header>
                  <h4 class="not-margin">Chào mừng đến với <b>HealthCare</b></h4>
                </template>
                <div class="con-content">
                  <p>Mọi thông tin thắc mắc, vui lòng liên hệ:</p>
                  <p><b>Số điện thoại:</b> 034 730 1368</p>
                  <p><b>Email:</b> healthcare@gmail.com</p>
                  <p>Xin trân trọng cảm ơn!</p>
                </div>
                <template #footer>
                  <div class="con-footer right-align">
                    <vs-button type="transparent" @click="support = false"> Ok </vs-button>
                  </div>
                </template>
              </vs-dialog>
            </div>
            <!-- <a href="http://localhost:8081"><vs-button style="height: 25px;" type="gradient" :active="active == 0">Quản lý</vs-button></a> -->
          </div>

        </div>

      </div>
    </div>

    <DisclosurePanel class="sm:hidden">
      <div class="space-y-1 px-2 pb-3 pt-2">
        <DisclosureButton v-for="item in navigation" :key="item.name" as="a" :href="item.href"
          :class="[item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white', 'block rounded-md px-3 py-2 text-base font-medium']"
          :aria-current="item.current ? 'page' : undefined">{{ item.name }}</DisclosureButton>
      </div>
    </DisclosurePanel>
  </Disclosure>
</template>

<script setup>
import { Disclosure, DisclosureButton, DisclosurePanel, Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/vue'
import { Bars3Icon, BellIcon, XMarkIcon } from '@heroicons/vue/24/outline'

const navigation = ref([
  { name: 'Tất cả', href: '#', current: true },
  { name: 'Tại nhà', href: '#', current: false },
  { name: 'Tại viện', href: '#', current: false },
])
import { useRouter } from 'vue-router';
import eventBus from '@/eventBus';

const router = useRouter()
import { ref, onMounted, computed, watchEffect } from 'vue';
const support = ref(false)

const isShowSearch = ref(true)
const clickSearch = () => {
  router.push('/timkiem')
  isShowSearch.value = false
}
// Watch for route changes
router.afterEach(() => {
  // If not on /timkiem, show the search button again
  if (router.currentRoute.value.path !== '/timkiem') {
    isShowSearch.value = true;
  }
});


// Trong phương thức chooseType
const chooseType = (item) => {
  console.log(item)
  eventBus.emit('selectedItem', item.name);
  navigation.value.forEach(navItem => {
    if (navItem.name === item.name) {
      navItem.current = true;
    } else {
      navItem.current = false;
    }

  });
}

</script>


<style scoped>
.searchIcon:hover {
  cursor: pointer;
}
.support:hover {
  cursor: pointer;
}
.right-align {
  justify-content: flex-end;
}
</style>