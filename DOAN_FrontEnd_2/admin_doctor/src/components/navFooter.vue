<template>
    <v-app>
      <v-footer class="d-flex flex-column" style="padding: 0 !important;">
        <div class="row py-5 w-100" style="background: rgb(237, 255, 250);">
          <div class="row col-xl-9 col-lg-10 col-sm-12" style="margin: auto;">
            <div class="col-xl-5 col-lg-5 col-md-7 col-sm-12 " >
            <h3 style="font-size: 13px; font-weight: bold; float: left;">Công ty Cổ phần Công nghệ BookingCare</h3>
            <br/>
              <div v-for="(item, index) in iconList1" :key="index" class="iconList1">
                <i :class="item.icon"></i> <!-- Hiển thị icon Font Awesome -->
                <span style="font-size: 13px; flex: 1;">{{item.text}}</span> <!-- Hiển thị text tương ứng -->
              </div>
              <div class="mt-2">
                <h3 style="font-size: 13px; font-weight: bold; float: left;">Văn phòng tại TP Hồ Chí Minh</h3>
                <br>
                <div class="iconList1">
                  <i class="fa-sharp fa-solid fa-location-dot"></i> <!-- Hiển thị icon Font Awesome -->
                  <span style="font-size: 13px; flex: 1;">Số 01, Hồ Bá Kiện, Phường 15, Quận 10</span> <!-- Hiển thị text tương ứng -->
                </div>
              </div>
              <div>
                <a class="flex" href="http://online.gov.vn/Home/AppDetails/1101">
                  <img src="registered.png" alt="">
                  <img src="registered.png" alt="">
                </a>
                <br>
              </div>
            </div>
          <div class="flex justify-center col-xl-3 col-lg-3 col-md-4 col-sm-4 col-5">
            <div>
              <img class="h-8 w-auto" src="logo.png" alt="Your logo" />
              <div class="mt-3" >
                <a style="text-decoration: none; color: black; font-size: 13px;" v-for="(text, index) in textList2" :key="index" href="#" class="link-item">{{ text }}<br/></a>
              </div>
              <br>
            </div>
          </div>
          <div class="col-xl-4 col-lg-4 col-md-12 col-sm-8 col-7">
            <div v-for="(item, index) in itemList3" :key="index" class="item-wrapper">
              <a :href="item.link" class="link-itemList3">
                <img class="" :src="item.img" alt="item image">
                <div class="content">
                  <h6>{{ item.title }}</h6>
                  <p>{{ item.text }}</p>
                </div>
              </a>
            </div>
          </div>
          </div>
        </div>

        <div class="row  w-100 align-center px-4" style="background-color: rgb(150 215 229/ 1);">
          <h6 class="col-6">© 2024 BookingCare.</h6>
            <ul class="social-icons col-6" style="display: flex; justify-content: space-evenly; align-items: center; margin: auto;  font-size: 40px;" >
                <li v-for="(item, index) in items" :key="index">
                <a :class="item.class" :href="item.url">
                    <font-awesome-icon :icon="['fab', item.title]" />
                </a>
                </li>
            </ul>
        </div>
      </v-footer>
    </v-app>
  </template>


  <script setup>
import { ref } from 'vue';


const items = [
  { 
    class: "tiktok",
    title: "tiktok",
    url: "https://www.tiktok.com/@songkhoesuotdoi"
  },
  { 
    class: "facebook",
    title: "facebook",
    url: "https://www.facebook.com/bookingcare"
  },
  { 
    class: "youtube",
    title: "youtube",
    url: "https://www.youtube.com/channel/UC9l2RhMEPCIgDyGCH8ijtPQ"
  },

];

const iconList1 = [
    { icon: 'fa-sharp fa-solid fa-location-dot', text: 'Lô B4/D21, Khu đô thị mới Cầu Giấy, Phường Dịch Vọng Hậu, Quận Cầu Giấy, Thành phố Hà Nội, Việt Nam' },
    { icon: 'fa-solid fa-vial-circle-check', text: 'ĐKKD số. 0106790291. Sở KHĐT Hà Nội cấp ngày 16/03/2015' },
    { icon: 'fa-solid fa-phone', text: '024-7301-2468 (7h - 18h)' },
    { icon: 'fa-solid fa-envelope', text: 'support@bookingcare.vn (7h - 18h)' },
];

const textList2 = ["Tuyển dụng", "Chính sách bảo mật", 
"Quy chế hoạt động", "Liên hệ hợp tác", "Điểu khoản sử dụng", "Câu hỏi thường gặp"];

const itemList3 = [ 
  {link:'https://hellodoctors.vn/', img:'hellodoctor.png', title:'Hello Doctor', text:'Bảo trợ chuyên mục nội dung "sức khỏe tinh thần"'},
  {link:'https://bernard.vn/', img:'bernard.png', title:'Hệ thống y khoa chuyên sâu quốc tế Bernard', text:'Bảo trợ chuyên mục nội dung "y khoa chuyên sâu"'},
  {link:'https://www.doctorcheck.vn/', img:'doctorcheck.png', title:'Doctor Check - Tầm Soát Bệnh Để Sống Thọ Hơn', text:'Bảo trợ chuyên mục nội dung "sức khỏe tổng quát"'},
]
</script>


<style scoped>
.iconList1 {
  display: flex;
}

.iconList1 i {
  width: 25px;
}

.iconList1 span {
  flex: 1;
}

.link-itemList3 {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: inherit;
}

.content {
  padding-left: 10px;
  font-size: 13px;
}
.content h6{
  font-size: 13px;
}

</style>