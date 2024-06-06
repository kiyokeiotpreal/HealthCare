<template>
    <div style="background: #f6f6f6;">
        <div class="p-10 pb-3" style="max-width: 800px; margin: auto;">
            <div style="width: 100%; margin: auto; display: flex;">
                <a href="">
                    <img :src="scheduleTime.avata" alt="" style="width: 80px; height: 80px; border-radius: 50%;">
                </a>
                <div style="margin-left: 5%;">
                    <p style="margin-bottom: 3px;">ĐẶT LỊCH KHÁM</p>
                    <p style="margin-bottom: 3px;">
                        <a href="" style="text-decoration: none; color: #337ab7; font-weight: 600 !important;">
                            {{ scheduleTime.position }}{{ scheduleTime.name }}
                        </a>
                    </p>
                    <p>{{ scheduleTime.time }} - {{ scheduleTime.selectedDay }}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="mb-10">
        <div class="" style="max-width: 800px; margin: auto;">
            <div class="mt-3 mb-3" style="width: 90%; margin: auto;">
                <div class="price" style="width: 200px; height: 60px; border: 1px red solid; 
                display: flex; flex-direction: column; align-items: center; justify-content: center;">
                    <div style="display: flex; align-items: center;">
                        <input type="radio" checked="checked" name="price">
                        <span style="margin-left: 5px;">Giá khám</span>
                    </div>
                    <p style="margin-bottom: 0;">{{ (scheduleTime.price) }}</p>
                </div>
            </div>
            <div class="form-input mt-4" style="width: 90%; margin: auto;">
                <a-form :model="formState">
                    <a-form-item>
                        <a-input v-model:value="formState.name" placeholder="Họ tên bệnh nhân ( bắt buộc )">
                            <template #prefix>
                                <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />
                            </template>
                        </a-input>
                    </a-form-item>
                    <p>Hãy ghi rõ Họ Và Tên, viết hoa những chữ cái đầu tiên, ví dụ: Trần Văn Phú</p>

                    <a-form-item label="">
                        <a-radio-group v-model:value="formState.gender">
                            <a-radio value="Nam">Nam</a-radio>
                            <a-radio value="Nữ">Nữ</a-radio>
                        </a-radio-group>
                    </a-form-item>

                    <a-form-item>
                        <a-input v-model:value="formState.year" placeholder="Năm sinh ( bắt buộc )">
                            <template #prefix>
                                <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />
                            </template>
                        </a-input>
                    </a-form-item>

                    <a-form-item>
                        <a-input v-model:value="formState.phone" placeholder="Số điện thoại liên hệ ( bắt buộc )">
                            <template #prefix>
                                <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />
                            </template>
                        </a-input>
                    </a-form-item>

                    <a-form-item>
                        <a-input v-model:value="formState.email" placeholder="Email ( bắt buộc )">
                            <template #prefix>
                                <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />
                            </template>
                        </a-input>
                    </a-form-item>

                    <a-form-item label="">
                        <a-textarea v-model:value="formState.reason" placeholder="Lý do khám ( bắt buộc )" />
                    </a-form-item>

                    <a-form-item label="">
                        <a-textarea v-model:value="formState.history" placeholder="Lịch sử khám bệnh ( nếu có )" />
                    </a-form-item>

                    <div class="">
                        <p class="" style="color: #337ab7; font-weight: bold;">Hình thức thanh toán</p>
                        <label>
                            <input class="dauvao-nhap" type="radio" name="pay_type" value="1" checked="checked"> Thanh
                            toán sau tại cơ sở y
                            tế
                        </label>
                    </div>

                    <div class="thanhtoan mt-3">
                        <div class="thanhtoan-item">
                            <div>Giá khám</div>
                            <div>{{ scheduleTime.price }}</div>
                        </div>
                        <div class="thanhtoan-item">
                            <div>Phí đặt lịch</div>
                            <div>Miễn phí</div>
                        </div>
                        <hr>
                        <div class="thanhtoan-item">
                            <div>Tổng cộng</div>
                            <div style="color: red;">{{ scheduleTime.price }}</div>
                        </div>
                    </div>

                    <p class="text-center" style="padding-top: 1em;">Quý khách vui lòng điền đầy đủ thông tin để tiết
                        kiệm thời gian làm thủ
                        tục khám</p>

                    <div class="dauvao-canhbao">
                        <p style="text-align: justify; "><b>LƯU Ý</b></p>
                        <p style="text-align: justify; ">Thông tin anh/chị cung cấp sẽ được sử dụng làm hồ sơ khám bệnh,
                            khi điền thông tin
                            anh/chị vui lòng:</p>
                        <ul>
                            <li style="text-align: justify; ">Ghi rõ họ và tên, viết hoa những chữ cái đầu tiên, ví
                                dụ:<b> Trần Văn
                                    Phú </b></li>
                            <li style="text-align: justify; ">Điền đầy đủ, đúng và vui lòng kiểm tra lại thông tin trước
                                khi ấn "Xác nhận đặt khám"
                            </li>
                        </ul>
                    </div>


                    <a-form-item class="mt-3" :wrapper-col="{ span: 14, offset: 8 }">
                        <vs-button ref="button" flat @click="booking(scheduleTime)">Xác nhận đặt khám</vs-button>
                    </a-form-item>


                </a-form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed, watchEffect } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const router = useRouter()

const store = useStore()

const scheduleTime = computed(() => store.state.scheduleTime);


import { reactive, toRaw } from 'vue';

const formState = ref({
    name: '',
    gender: '',
    year: '',
    phone: '',
    email: '',
    reason: '',
    history: '',
});

import { VsLoadingFn } from 'vuesax-alpha'

const booking = async (scheduleTime) => {
    let loadingInstance;
    try {
        let showAlert = true; // Biến flag để xác định xem alert đã được hiển thị hay không

        loadingInstance = VsLoadingFn({
            text: 'Loading...',
        });

        setTimeout(() => {
            if (showAlert) {
                loadingInstance.close(); // Đóng loading nếu alert chưa được hiển thị
            }
        }, 20000);

        const bookingScheduleRequest = {
            scheduleID: scheduleTime.scheduleID,
            namePatient: formState.value.name,
            phonePatient: formState.value.phone,
            emailPatient: formState.value.email,
            genderPatient: formState.value.gender,
            yearPatient: formState.value.year,
            reason: formState.value.reason,
            medicalHistoryPatient: formState.value.history,
        };

        // Lưu thông tin đặt khám vào LocalStorage
        const bookedAppointments = JSON.parse(localStorage.getItem('bookedAppointments')) || [];
        bookedAppointments.push({
            scheduleID: scheduleTime.scheduleID,
            namePatient: formState.value.name,
            time: scheduleTime.time,
            nameClinic: scheduleTime.nameClinic,
            addressClinic: scheduleTime.addressClinic,
            selectedDay: scheduleTime.selectedDay,
            avata: scheduleTime.avata,
            name: scheduleTime.name,
            position: scheduleTime.position,
            price: scheduleTime.price
        });

        // Kiểm tra giá trị của bookingScheduleRequest
        console.log('Booking Schedule Request:', bookingScheduleRequest);

        const response = await axios.put("http://localhost:8088/api/v1/user/bookingSchedule", bookingScheduleRequest);

        // Xác nhận đặt lịch thành công
        alert("Đặt lịch thành công");
        showAlert = false; // Đặt biến flag thành false để ngăn loading đóng lại

        localStorage.setItem('bookedAppointments', JSON.stringify(bookedAppointments));
        router.push("/");
    } catch (error) {
        if (error.response.status == "404") {
            alert("Đặt thất bại ! Khách hàng khác đã đặt lịch trình này, vui lòng chọn lịch khác");
        }
        if (error.response.status == "400") {
            alert("Vui lòng nhập đầy đủ các thông tin cần thiết");
        }
        if (error.response.status == "500") {
            alert("Lỗi kết nối");
        }
    } finally {
        loadingInstance.close(); // Đóng loading khi kết thúc
    }
};



const handleFileChange = (event) => {
    // Lưu thông tin về file được chọn vào formState
    const file = event.target.files[0];
    formState.value.fileName = file ? file.name : '';
};


</script>

<style scoped>
.form-input {
    margin-left: auto;
    /* Đặt margin-left: auto để căn phải */
}

.ant-form-item {
    margin-bottom: 10px !important;
}

.thanhtoan {
    padding: 15px;
    background: #f6f6f6;
}

.thanhtoan-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.dauvao-canhbao {
    background: #D4EFFC;
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 3px;
    margin-bottom: 15px;
    font: 14px / 1.5 'Montserrat', sans-serif;
}
</style>