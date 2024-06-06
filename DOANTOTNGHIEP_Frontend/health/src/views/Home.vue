<template>  
    <div class="mt-2" style="width: 90%; margin: auto">
      <v-carousel
                height=""
                width=""
                :show-arrows="hover"
                :cycle="true"
                :hide-delimiter-background="true"
                interval="4000"
            >
                <v-carousel-item v-for="(slide, i) in slides" :key="i" >
                <v-sheet height="100%">
                    <div class="d-flex fill-height justify-center align-center ">
                        <a href="">
                            <div class="text-h2">
                                <img :src="slide.src" alt="" style="border-radius: 20px; max-height: 60vh; width: 80vw; object-fit: cover; ">
                            </div>
                        </a>
                    </div>
                </v-sheet>
                </v-carousel-item>
            </v-carousel>
    </div>

    <div class="mt-4" style="width: 80%; margin: auto;">
        <div class="comprehensive-service  mt-3 col-xl-12 " 
        style=" font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif !important; "
        v-if="typeNameService==='Tất cả' || typeNameService==='Tại viện'">
            <div>
                <h2>Dịch vụ toàn diện</h2>
            </div>
            <div class="row mt-4">
                <div v-for="item in fullService1" :key="item.text" class="col-6  itemService">
                    <div style="width: 100%; height: 100%; border: 2px solid rgb(241, 238, 238);; border-radius: 20px; margin-left: 0; ">
                        <router-link :to="`/dichvukham/${item.slug}`">
                            <a href="#" style="display: flex;">
                                <div style="display: flex; align-items: center;">
                                    <img :src="item.img" alt="image" style="max-width: 100%; max-height: 100%;">
                                </div>
                                <div style="display: flex; align-items: center; flex: 1; ">
                                    <h3 style="margin-bottom: 0; font-size: 3vw;">{{ item.text }}</h3>
                                </div>
                            </a>
                        </router-link>
                    </div>
                </div>

                <div v-for="item in fullService2" :key="item.text" class="col-6  itemService">
                    <div style="width: 100%; height: 100%; border: 2px solid rgb(241, 238, 238);; border-radius: 20px; margin-left: 0; ">
                            <router-link :to="`/dichvukham-s2/${item.slug}`" >
                                <a href="" style="display: flex; ">
                                    <div style="display: flex; align-items: center;">
                                        <img :src="item.img" alt="image" style="max-width: 100%; max-height: 100%;">
                                    </div>
                                    <div style="display: flex; align-items: center; flex: 1; ">
                                        <h3 style="margin-bottom: 0; font-size: 3vw;">{{ item.text }}</h3>
                                    </div>
                                </a>
                            </router-link>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="slider-container" style=" font-family: Montserrat, sans-serif !important; font-size: 13px;" v-if="typeNameService==='Tất cả' || typeNameService==='Tại viện'">
        <div style="display: flex; align-items: center; justify-content: space-between;">
            <h2>Chuyên khoa</h2>
            <div style="font-family: Montserrat, sans-serif !important;  ;
            background: rgb(218 243 246/ 1) !important; border-radius: 1rem !important; padding: 10px;">
                <router-link to="/dichvukham/kham-chuyen-khoa" style="text-decoration: none; color: darkblue; " >
                    <a href="" style="text-decoration: none; color: darkblue; ">
                        <h4 style="margin-bottom: 0; font-size: 2vw;">Xem thêm</h4>
                    </a>
                </router-link>
            </div>
        </div>
        
        <div class="slide-bar">
            <div class="slider" style="margin-top: 2%;">
            <!-- Vòng lặp để hiển thị các mục từ mảng slides2 -->
            <div v-for="(slide, index) in specialist" :key="index" class="">
            <div class="slide-item">
                <a href="" style="text-decoration: none; color: black;" @click.prevent="getDataSlideSpecialist(slide.name)">
                    <img :src="slide.img" alt="slide" class="slide-img">
                    <h4 class="slide-title">{{ slide.name }}</h4>
                </a>
            </div>
            </div>
            </div>
        </div>
    </div>

    <div class="slider-container mt-10" style=" font-family: Montserrat, sans-serif !important; font-size: 13px;"
    v-if="typeNameService==='Tất cả' || typeNameService==='Tại nhà'">
        <div style="display: flex; align-items: center; justify-content: space-between;">
            <h2>Khám từ xa</h2>
            <div style="font-family: Montserrat, sans-serif !important;  ;
            background: rgb(218 243 246/ 1) !important; border-radius: 1rem !important; padding: 10px;">
            <router-link to="/dichvukham/kham-tu-xa" style="text-decoration: none; color: darkblue; " >
                    <a href="" style="text-decoration: none; color: darkblue; ">
                        <h4 style="margin-bottom: 0; font-size: 2vw;">Xem thêm</h4>
                    </a>
                </router-link>
            </div>
        </div>
        <div class="slide-bar">
            <div class="slider" style="margin-top: 2%;">
            <!-- Vòng lặp để hiển thị các mục từ mảng slides2 -->
            <div v-for="(slide, index) in remote" :key="index" class="">
            <div class="slide-item">
                <a href="" style="text-decoration: none; color: black;" @click.prevent="getDataSlideRemote(slide.name)">
                    <img :src="slide.img" alt="slide" class="slide-img">
                    <h4 class="slide-title">{{ slide.name }}</h4>
                </a>
            </div>
            </div>
            </div>
        </div>
    </div>

</template>

<script setup>
import navbar from '@/components/navbar.vue'
import navFooter from '../components/navFooter.vue'
import {ref, computed, watch, onMounted} from 'vue'
import { text } from '@fortawesome/fontawesome-svg-core';
import  Axios  from 'axios';
import eventBus from '@/eventBus';
import { list } from 'postcss';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const router = useRouter()

const store = useStore() 

const typeNameService = ref('Tất cả')


// Trong phần setup
onMounted(() => {
  eventBus.on('selectedItem', (itemName) => {
    // Xử lý itemName tại đây
    console.log('Received item name:', itemName);
    typeNameService.value = itemName
  });
});

const listExam = computed(() => {
    return store.state.listExam
}) 



const slides = ref([
  { src: 'slide1.png' },
  { src: 'slide5.png' },
]);

const fullService1 = [
    { img: "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161905-iconkham-chuyen-khoa.png", text:"Khám Chuyên khoa", slug:"kham-chuyen-khoa"},
    { img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161817-iconkham-tu-xa.png", text:"Khám từ xa",  slug:"kham-tu-xa"},
    { img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161403-iconsuc-khoe-tinh-than.png", text:"Sức khoẻ tinh thần", slug:"kham-tinh-than"},
    // { img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161421-icongoi-phau-thuat.png", text:"Gói Phẫu thuật", slug:"goi-phau-thuat"},

]

const fullService2 = [
    { img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161350-iconkham-tong-quan.png", text:"Khám tổng quát",  slug:"kham-tong-quat"},
    { img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161340-iconxet-nghiem-y-hoc.png", text:"Xét nghiệm y học",  slug:"xet-nghiem-y-hoc"},
]



const getDataSlideSpecialist = async(speOrExamName) => {
    try {
        store.dispatch("getDataSche", speOrExamName )
        router.push("/lichtrinh", )
  } catch (error) {
    alert("error");
  }
}

const getDataSlideRemote = async(speOrExamName) => {
    try {
        store.dispatch("getDataSche", speOrExamName )
        router.push("/lichtrinh", )
  } catch (error) {
    alert("error");
  }
}



const specialist = ref([
]);

onMounted(
    async() => {
    try {
        const response = await Axios.get("http://localhost:8088/api/v1/user/getExaminationService?slugServiceType=" + "kham-chuyen-khoa");
        specialist.value = response.data;
    } catch (error) {
        alert("error")
    }
}
)

const remote = ref([
]);

onMounted(
    async() => {
    try {
        const response = await Axios.get("http://localhost:8088/api/v1/user/getExaminationService?slugServiceType=" + "kham-tu-xa");
        remote.value = response.data;
    } catch (error) {
        alert("error")
    }
}
)




</script>

<style scoped>
.itemService {
    /* border: 2px solid rgb(241, 238, 238);
    border-radius: 20px; */
    height: 100px;
    display: flex;
    align-items: center;
    justify-content: center;
    /* margin-left: 5%; */
    margin-bottom: 3%;
}

.itemService a {
    margin-left: 10% ;
    display: flex;
    gap: 5%;
    text-decoration: none;
    color: black;
    height: 100%;
    width: 100%;
}
    @media (min-width: 0) {
            .comprehensive-service h2 {
                font-size: 17px !important;
                
            }
            .slider-container h2 {
                font-size: 15px !important;
            }
            
            .itemService {
                height: 50px;
            }
            .itemService img {
                width: 25px;
            }
            .itemService h3 {
            font-size: 13px !important;
            }
            .image-title {
            font-size: 10px;
            }
            .slider-container h4{
            font-size: 11px !important;
        }
        }
        @media (min-width: 576px) {
            .comprehensive-service h2 {
                font-size: 22px !important;
            }
            .slider-container h2 {
                font-size: 20px !important;
            }
            .itemService {
                height: 50px;
            }
            .itemService img {
                width: 25px;
            }
            .itemService h3 {
            font-size: 15px !important;
            }
            .image-title {
            font-size: 10px;
            }
            .slider-container h4{
            font-size: 13px !important;
        }
        }
    @media (min-width: 768px) {
        .comprehensive-service h2 {
                font-size: 28px !important;
            }
            .slider-container h2 {
                font-size: 25px !important;
            }
        .itemService {
                height: 70px;
            }
        .itemService img {
            width: 35px;
        }
        .itemService h3 {
            font-size: 20px !important;
        }
        .image-title {
            font-size: 13px;
        }
        .slider-container h4{
            font-size: 18px !important;
        }
    }
    @media (min-width: 992px) {
        .comprehensive-service h2 {
                font-size: 40px !important;
            }
            .slider-container h2 {
                font-size: 35px !important;
            }
        .itemService {
                height: 90px;
            }
        .itemService img {
            width: 45px;
        }
        .itemService h3 {
            font-size: 25px !important;
        }
        .image-title {
            font-size: 16px;
        }
        .slider-container h4{
            font-size: 21px !important;
        }
    }

    @media (min-width: 1200px) {
        .itemService {
                height: 100px;
            }
        .itemService img {
            width: 50px;
            height: auto;
        }
        .itemService h3 {
            font-size: 28px !important;
        }
        .image-title {
            font-size: 22px;
        }
        .slide-title {
            text-align: center;
            margin-top: 5px;
            font-size: 25px !important;
        }
        .slider-container h4{
            font-size: 23px !important;
        }
    }

.image-title {
    text-align: center; /* Căn giữa văn bản */
    color: black;
    font-weight: 400;
}


.slider-container {

  width: 80%;
  margin: auto;
  
}
.slide-bar {

    overflow-x: auto;
    white-space: nowrap; /* Ngăn các mục bị xuống dòng */
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif !important; 
}
::-webkit-scrollbar{
    width: 10px;
    background: white;
}
::-webkit-scrollbar-thumb{
    width: 25%;
    background: rgb(226, 226, 226);
    border-radius: 10px;
}


.slider {
  display: inline-flex; /* Hiển thị các mục trên cùng một hàng */
}

.slide-item {
    margin-right: 20px;
  width: 30vw; /* Sử dụng 30% chiều rộng của viewport */
  max-width: 300px; /* Tối đa 300px để tránh co quá mức */
}

.slide-img { 
    width: 100%;
    height: auto; /* Đảm bảo tỷ lệ khung hình được duy trì */
    object-fit: cover; 
}

.slide-title {
  text-align: center;
  margin-top: 10px;
  padding-bottom: 3vh;
  font-size: 2px;
}


</style>