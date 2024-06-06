<template>
  <div>
    <div class="d-flex flex-column" style="padding: 0 !important;">
      <div class="row py-5 w-100" style="background: rgb(237, 255, 250);">
        <div class="row col-xl-9 col-lg-10 col-sm-12" style="margin: auto;">
          <div class="col-xl-5 col-lg-5 col-md-7 col-sm-12 ">
            <h3 style="font-size: 13px; font-weight: bold; float: left;">Công ty Cổ phần Công nghệ BookingCare</h3>
            <br />
            <div v-for="(item, index) in iconList1" :key="index" class="iconList1">
              <i :class="item.icon"></i> <!-- Hiển thị icon Font Awesome -->
              <span style="font-size: 13px; flex: 1;">{{ item.text }}</span> <!-- Hiển thị text tương ứng -->
            </div>
            <div class="mt-2">
              <h3 style="font-size: 13px; font-weight: bold; float: left;">Văn phòng tại TP Hồ Chí Minh</h3>
              <br>
              <div class="iconList1">
                <i class="fa-sharp fa-solid fa-location-dot"></i> <!-- Hiển thị icon Font Awesome -->
                <span style="font-size: 13px; flex: 1;">Số 01, Hồ Bá Kiện, Phường 15, Quận 10</span>
                <!-- Hiển thị text tương ứng -->
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
              <div class="flex">
                <img class="h-8 w-auto"
                  src="data:image/png;base64,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"
                  alt="Your logo" style="border-radius: 100%;" />
                <h4 style="margin-bottom: 0; color: #49bce2; font-weight: 550;">HealthCare</h4>
              </div>
              <div class="mt-3">
                <a style="text-decoration: none; color: black; font-size: 13px; margin-left: 15%;" v-for="(text, index) in textList2"
                  :key="index" href="#" class="link-item">{{ text }}<br /></a>
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
        <h6 class="col-6">© 2024 HealthCare.</h6>
        <ul class="social-icons col-6"
          style="display: flex; justify-content: space-evenly; align-items: center; margin: auto;  font-size: 40px;">
          <li v-for="(item, index) in items" :key="index">
            <a :class="item.class" :href="item.url">
              <font-awesome-icon :icon="['fab', item.title]" />
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
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
  { link: 'https://hellodoctors.vn/', img: 'hellodoctor.png', title: 'Hello Doctor', text: 'Bảo trợ chuyên mục nội dung "sức khỏe tinh thần"' },
  { link: 'https://bernard.vn/', img: 'bernard.png', title: 'Hệ thống y khoa chuyên sâu quốc tế Bernard', text: 'Bảo trợ chuyên mục nội dung "y khoa chuyên sâu"' },
  { link: 'https://www.doctorcheck.vn/', img: 'doctorcheck.png', title: 'Doctor Check - Tầm Soát Bệnh Để Sống Thọ Hơn', text: 'Bảo trợ chuyên mục nội dung "sức khỏe tổng quát"' },
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

.content h6 {
  font-size: 13px;
}
</style>