<template>
  <el-container>
    <el-header height="10vh" style="margin: 0%;padding: 0">
      <Header :isAuth="isAuth"> </Header>
    </el-header>

    <el-affix :offset="-20">
      <el-menu class="search-form card" style="background-color: #1d3043;">
        <el-input class="city-input" v-model="city" style="font-size: 18px;" :prefix-icon="'search'" />

        <button @click="openStartDatePicker" class="date-picker-button" style="align-items: center;cursor: pointer;">
          <el-icon size="25">
            <Calendar />
          </el-icon>
          <div class="date-info">
            <div class="selected-date">{{ formattedStartDate }}</div>
            <div class="selected-day">{{ startDayOfWeek }}</div>
          </div>
        </button>

        <button @click="openEndDatePicker" class="date-picker-button" style="align-items: center;cursor: pointer;">
          <el-icon size="25">
            <Calendar />
          </el-icon>
          <div class="date-info">
            <div class="selected-date">{{ formattedEndDate }}</div>
            <div class="selected-day">{{ endDayOfWeek }}</div>
          </div>
        </button>

        <!-- 隐藏的输入框用于初始化 Flatpickr -->
        <input type="text" id="live-time" class="search-calender"
          style="z-index: -1; position: relative; width: 0; position: relative; left: -8vw;"
          :style="{ left: calenderLeft + 'px', top: '20px' }">

        <el-button class="search-button" @click="search()">搜索</el-button>

      </el-menu>
    </el-affix>

    <div class="container">
      <div class="hotel-images" v-if="hotelDetails.url">
        <img class="hotel-top-image" :src="hotelDetails.url[4]" alt="">
        <div class="hotel-images-two">
          <img class="hotel-image" :src="hotelDetails.url[0]" alt="">
          <img class="hotel-image" :src="hotelDetails.url[1]" alt="">
        </div>
        <div class="hotel-images-two">
          <img class="hotel-image" :src="hotelDetails.url[2]" alt="">
          <img class="hotel-image" :src="hotelDetails.url[3]" alt="">
        </div>
        <div class="hotel-images-two">
          <img class="hotel-image" :src="hotelDetails.url[5]" alt="">
          <img class="hotel-image" :src="hotelDetails.url[6]" alt="">
        </div>
      </div>

      <el-affix :offset="65" @change="handleAffixChange">
        <el-menu class="nav-menu card" :class="{ 'affix-active': isAffixActive }">
          <el-menu-item :class="{ 'is-active': activeSection === 'introduction' }"
            @click="scrollTo('introRef')">简介</el-menu-item>
          <el-menu-item :class="{ 'is-active': activeSection === 'service' }"
            @click="scrollTo('service')">设施服务</el-menu-item>
          <el-menu-item :class="{ 'is-active': activeSection === 'rooms' }" @click="scrollTo('rooms')">客房</el-menu-item>
          <el-menu-item :class="{ 'is-active': activeSection === 'rate' }" @click="scrollTo('rate')">住客评分</el-menu-item>
          <el-menu-item :class="{ 'is-active': activeSection === 'location' }"
            @click="scrollTo('location')">位置</el-menu-item>
        </el-menu>
      </el-affix>

      <div class="intro-container" v-if="hotelDetails.name">
        <div class="intro-left">
          <div class="card introduction" id="introduction" ref="introductionRef">
            <div class="hotel-name">{{ hotelDetails.name }}</div>
            <span v-for="n in star" :key="n">
              <img class="star" src="../assets/星.png" />
            </span>
            <div class="hotel-location">{{ hotelDetails.location }}</div>
            <div class="line" style="position:relative; width: 96%; left: 2%; margin-top: 5px;"></div>
            <div class="hotel-intro">{{ hotelDetails.introduction }}</div>
          </div>

          <div class="card facility" id="service" ref="serviceRef">
            <div class="facility-name">重点设施</div>
            <div class="facilities">
              <div class="facility-content">
                <img src="../assets/like.svg">
                <div class="fac-content-name">活动体验一流</div>
              </div>
              <div class="facility-content">
                <img src="../assets/swimming-pool.svg">
                <div class="fac-content-name">泳池很赞</div>
              </div>
              <div class="facility-content">
                <img src="../assets/transfer.svg">
                <div class="fac-content-name">机场接送服务</div>
              </div>
              <div class="facility-content">
                <img src="../assets/forest.svg">
                <div class="fac-content-name">景观很赞</div>
              </div>
              <div class="facility-content">
                <img src="../assets/medal.svg">
                <div class="fac-content-name">性价比之选</div>
              </div>
            </div>
          </div>
        </div>

        <div class="intro-right" id="rate" ref="rateRef">
          <div class="card rate">
            <div class="rate-header">
              <div class="score"> 8.4 棒棒哒</div>
              <div class="rate-num">3,303 个评分</div>
            </div>
            <el-carousel :interval="4000" type="card" height="12vh" indicator-position="outside">
              <el-carousel-item v-for="review in reviews" :key="review.id">
                <div class="review-card">
                  <div class="review-content">"{{ review.comment }}"</div>
                  <div class="reviewer-name">{{ review.name }}</div>
                </div>
              </el-carousel-item>
            </el-carousel>
          </div>

          <div class="card map-card" id="location" ref="locationRef">
            <img src="../assets/property-map-entry.svg" alt="地图" class="map-image" @click="openMapDialog()" />
          </div>
        </div>

      </div>

      <div class="rooms card" id="rooms" ref="roomsRef">
        <div v-for="(room, index) in roomDetails" :key="index">
          <div class="room">
            <div class="room-name">{{ room.name }}</div>
          </div>
          <div class="room-detail">
            <div class="room-image" style="display: flex; pisition: relative; flex-direction: column;">
              <img :src="`../src/assets/${room.url}`" style="width: 15vw; height: 20vh" />
            </div>
            <div class="room-information">
              <div style="display: flex; pisition: relative; flex-direction: row;">
                <img src="../assets/jiudian_chuang.svg" style=" width: 2vw;" />
                <div class="room-inf-text">{{ room.numberOfWindows }} 张床</div>
              </div>
              <div style="display: flex; pisition: relative; flex-direction: row; margin-top: 1vh;">
                <img src="../assets/fangjianmianji.svg" style=" width: 2vw;" />
                <div class="room-inf-text">{{ room.area }}㎡</div>
              </div>
              <div style="display: flex; pisition: relative; flex-direction: row; margin-top: 1vh;">
                <img src="../assets/yangtai.svg" style=" width: 2vw;" />
                <div class="room-inf-text">美丽景观</div>
              </div>
              <div style="display: flex; pisition: relative; flex-direction: row; margin-top: 1vh;">
                <img src="../assets/jinzhixiyan.svg" style=" width: 2vw;" />
                <div class="room-inf-text">禁止吸烟</div>
              </div>
              <div style="display: flex; pisition: relative; flex-direction: row; margin-top: 1vh;">
                <img src="../assets/bathed.svg" style=" width: 2vw;" />
                <div class="room-inf-text">沐浴</div>
              </div>
            </div>
            <div class="room-discount">
              <div style="display: flex; pisition: relative; flex-direction: row;">
                <img src="../assets/check_mark.svg" style=" width: 2vw;" />
                <div class="room-inf-text">优惠特价！随时退款</div>
              </div>
              <div style="display: flex; pisition: relative; flex-direction: row;">
                <img src="../assets/check_mark.svg" style=" width: 2vw;" />
                <div class="room-inf-text">立即确认</div>
              </div>
              <div style="display: flex; pisition: relative; flex-direction: row;">
                <img src="../assets/check_mark.svg" style=" width: 2vw;" />
                <div class="room-inf-text">停车服务, 免费WiFi, 饮用水, 健身中心</div>
              </div>
            </div>
            <div class="room-price-pay">
              <div class="room-price">RMB {{ hotelDetails.prices[index * hotelDetails.rooms.length] }}</div>
              <el-button class="room-pay" @click="openPayDialog(index)">立即预订</el-button>
              <div class="room-quick">我们仅剩最后{{ hotelDetails.remains[index * hotelDetails.rooms.length] }}件房！</div>
            </div>
          </div>
          <div class="line" style="margin-top: 2vh"></div>
        </div>
      </div>


      <el-dialog v-model="mapVisible" title="位置" width="70vw" :before-close="handleClose">
        <div id="dia-map"></div>
      </el-dialog>

      <el-dialog v-model="payVisible" title="预订信息" @opened="initDatePicker">
        <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
          <el-form-item label="住宿时间">
            <!-- <el-date-picker v-model="form.date" type="daterange" range-separator="至" start-placeholder="请选择入住日期"
              end-placeholder="离店日期" format="YYYY/MM/DD" :picker-options="pickerOptions" :size="large" /> -->
            <input style="font-size: large;" type="text" v-model="testmodeldate" id="datepicker" class="form-calender">
          </el-form-item>

          <el-form-item label="房间数">
            <el-select v-model="form.rooms" placeholder="请选择房间数" @change="updateIdNumberFields">
              <el-option v-for="n in computedRoomNum" :key="n" :label="n + ' 间'" :value="n"></el-option>
            </el-select>
          </el-form-item>

          <!-- 身份证号输入 -->
          <el-form-item v-for="(id, index) in form.names" :key="index" :label="'住客姓名 ' + (index + 1)" prop="names">
            <el-input v-model="form.names[index]"></el-input>
          </el-form-item>

          <!-- 联系手机 -->
          <el-form-item label="联系手机" prop="phone">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>

          <!-- 预计到店时间 -->
          <el-form-item label="预计到店时间">
            <el-time-picker v-model="form.arrivalTime" placeholder="选择时间"
              :picker-options="{ selectableRange: '08:00:00 - 23:59:59' }"></el-time-picker>
          </el-form-item>

          <el-form-item label="备注">
            <el-input v-model="form.orderNote"></el-input>
          </el-form-item>

          <el-form-item label="总价">
            <span>{{ computedTotalPrice }} 元</span>
          </el-form-item>

          <!-- 提交按钮 -->
          <el-form-item>
            <el-button type="primary" @click="onSubmit">支付</el-button>
            <el-button @click="this.payVisible = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <!-- <button @click="test">test</button> -->

    </div>
  </el-container>
</template>

<script>
import Header from "@/components/Header.vue"
import { ref, onMounted, onUnmounted } from 'vue';
import flatpickr from "flatpickr";
import 'flatpickr/dist/flatpickr.min.css';
import "flatpickr/dist/themes/material_blue.css";
import MapContainer from "@/components/MapContainer.vue";
import AMapLoader from '@amap/amap-jsapi-loader';
import { ElNotification } from 'element-plus'


export default {
  name: 'Detail',

  components: {
    Header,
    MapContainer
  },

  data() {
    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);
    const dayAfterTomorrow = new Date(today);
    dayAfterTomorrow.setDate(dayAfterTomorrow.getDate() + 2);

    return {
      msg: 'Welcome to Your Vue.js App',
      isAuth: this.$store.getters.isAuthenticated,
      isAffixActive: false,
      getForm: {
        name: ''
      },
      hotelDetails: '',
      roomDetails: [],
      activeSection: '',
      in_date: this.$store.getters.date[0],
      left_date: this.$store.getters.date[1],
      city: this.$store.getters.city,
      fp: '',
      startDate: this.$store.getters.date[0],
      endDate: this.$store.getters.date[1],
      star: 5,
      reviews: [
        { id: 1, comment: "环境很安静，很喜欢", name: "齐木" },
        { id: 2, comment: "附件有很多好吃的阿鲁", name: "神乐" },
        { id: 2, comment: "位置很棒，距离将军府很近", name: "水果宾治武士" },
      ],
      mapVisible: false,
      iframeSrc: '',
      address: '1600 Amphitheatre Parkway, Mountain View, CA',
      map: '',
      mapLocation: [118.77943, 32.055015],
      calenderLeft: -400,
      payVisible: false,
      form: {
        roomindex: 0,
        rooms: 1,
        names: [''],
        phone: '',
        arrivalTime: '',
        date: [this.in_date, this.left_date],
        orderNote: ''
      },
      datePrices: {
        '2023-12-22': '$100',
        '2023-01-02': '$110',
        '2023-01-03': '$120',
        // ... 其他日期
      },
      testdate: [],
      testmodeldate: '2023-01-03',
      rules: {
        names: [
          { validator: this.validateNames, trigger: 'blur' },
          { required: true }
        ],
        phone: [
          { required: true, message: '请输入联系手机', trigger: 'blur' },
          { pattern: /^(13[0-9]|14[5|7]|15[0-3,5-9]|16[6]|17[0-8]|18[0-9]|19[8|9])\d{8}$/, message: '手机号码格式不正确', trigger: 'blur' }
        ]
      },
    }
  },

  created() {
    const m = this.$route.params.a;
    this.getForm = {
      name: m
    }
    this.name = m;
    this.$axios
      .post('http://localhost:8080/hotel/hotelDetail', this.getForm)
      .then((response) => {
        this.hotelDetails = response.data.data;
        const front = "https://i0.wp.com/"
        for (var i = 0; i < 7; i++) {
          this.hotelDetails.url[i] = front.concat(this.hotelDetails.url[i].substring(2, this.hotelDetails.url[i].length - 7).concat("1024*768"))
        }

        for (let i = 0; i < this.hotelDetails.rooms.length; i++) {
          this.getForm = {
            id: this.hotelDetails.rooms[i]
          }
          this.$axios
            .post('http://localhost:8080/room/roomDetail', this.getForm)
            .then((response) => {
              this.roomDetails[i] = response.data.data;
              console.log(this.roomDetails);
            })
            .catch((error) => {
              console.error('Error fetching hotel details:', error);
            });
        }

      })
      .catch((error) => {
        console.error('Error fetching hotel details:', error);
      });
    this.form.date[0] = this.$store.getters.date[0].toISOString().substring(0, 10)
    this.form.date[1] = this.$store.getters.date[1].toISOString().substring(0, 10)
    this.testmodeldate = this.$store.getters.date[0].toISOString().substring(0, 10) + ' to ' + this.$store.getters.date[1].toISOString().substring(0, 10)
  },

  computed: {
    formattedStartDate() {
      return this.formatDate(this.startDate);
    },
    formattedEndDate() {
      return this.formatDate(this.endDate);
    },
    startDayOfWeek() {
      return this.formatDayOfWeek(this.startDate);
    },
    endDayOfWeek() {
      return this.formatDayOfWeek(this.endDate);
    },
    computedTotalPrice() {
      let inday = new Date(this.form.date[0])
      let outday = new Date(this.form.date[1])
      let differenceInMilliseconds = outday - inday;
      let nights = differenceInMilliseconds / (1000 * 60 * 60 * 24)
      let price = 0;
      for (let i = 0; i < nights; i++) {
        price += parseInt(this.hotelDetails.prices[this.form.roomindex * this.hotelDetails.rooms.length + i]);
      }
      return price * this.form.rooms;
    },
    computedRoomNum() {
      console.log(this.form.date)
      let inday = new Date(this.form.date[0])
      let outday = new Date(this.form.date[1])
      let differenceInMilliseconds = outday - inday;
      let nights = differenceInMilliseconds / (1000 * 60 * 60 * 24)
      let minRoom = 10;
      for (let i = 0; i < nights; i++) {
        minRoom = Math.min(minRoom, parseInt(this.hotelDetails.remains[this.form.roomindex * this.hotelDetails.rooms.length + i]));
      }
      console.log("minRoom:" + minRoom);
      return minRoom;
    }
  },

  methods: {
    test() {
      console.log("test")
      console.log(this.roomDetails)
    },

    scrollTo(sectionId) {
      var element = this.$refs.introductionRef
      if (sectionId === 'service')
        element = this.$refs.serviceRef;
      else if (sectionId === 'location')
        element = this.$refs.locationRef;
      else if (sectionId === 'rooms')
        element = this.$refs.roomsRef;
      const top = element.offsetTop - 65 - 57.6
      console.log('top', top)
      if (element) {
        window.scrollTo({
          top: top,
          behavior: 'smooth'
        });
      }
    },

    handleAffixChange(affixed) {
      this.isAffixActive = affixed;
      console.log(this.isAffixActive);
    },

    onScroll() {
      const sections = ['introduction', 'rooms', 'service', 'rate', 'location'];
      const threshold = 150;

      for (let i = 0; i < sections.length; i++) {
        const element = document.getElementById(sections[i]);
        if (element) {
          const rect = element.getBoundingClientRect();
          if (rect.top <= threshold && rect.bottom >= threshold) {
            this.activeSection = sections[i];
            return; // 找到当前活动部分后立即退出循环
          }
        }
      }
    },

    search() {
      let self = this;
      let storeDate = [new Date(this.startDate), new Date(this.endDate)]
      console.log(this.startDate)
      this.$store.dispatch('search', { date: storeDate, city: this.hotelDetails.city });
      this.$router.push({
        name: 'HotelList',
        query: {
          form: JSON.stringify({
            name: this.hotelDetails.city,
            date: storeDate
          })
        }
      });
    },

    openStartDatePicker() {
      this.calenderLeft = -380;
      let today = new Date();
      today.setHours(0, 0, 0, 0);
      if (this.fp) {
        this.fp.set('minDate', "today");
        setTimeout(() => {
          this.fp.open();
        }, 100);
      }
    },


    openEndDatePicker() {
      this.calenderLeft = -210;
      if (this.fp) {
        setTimeout(() => {
          this.fp.open();
          if (this.fp.selectedDates.length > 0) {
            const minDate = this.fp.selectedDates[0];
            this.fp.set('minDate', minDate);
          }
        }, 100);
      }
    },

    formatDate(date) {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
      const formattedDate = date.toLocaleDateString('zh-CN', options);

      // 将日期转换为 "YYYY年MM月DD日" 的格式
      const parts = formattedDate.split('/');
      const yyyy = parts[0];
      const mm = parts[1];
      const dd = parts[2];

      const formattedDateInChinese = `${yyyy}年${mm}月${dd}日`;

      return formattedDateInChinese;
    },

    formatDayOfWeek(date) {
      const days = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
      return days[date.getDay()];
    },

    onDateChange(selectedDates) {
      // 当 Flatpickr 日期改变时更新 startDate 和 endDate
      if (selectedDates.length === 2) {
        this.startDate = selectedDates[0];
        this.endDate = selectedDates[1];
      }
    },

    moveToNewCenter() {
      const newLng = 118.796877; // 新的经度
      const newLat = 32.060255; // 新的纬度
      console.log(this.$refs.mapContainer)
      this.$refs.mapContainer.value.updateCenter();
    },

    openMapDialog() {
      // 构建Google Maps的URL
      this.mapVisible = true;
      this.mapLocation[0] = this.hotelDetails.longitude;
      this.mapLocation[1] = this.hotelDetails.latitude;

      AMapLoader.load({
        key: "21410e8154ca3fe8feaa2141fd4f4b02", // 你的高德地图API Key
        version: "2.0",
        plugins: [],
      })
        .then((AMap) => {
          this.map = new AMap.Map("dia-map", {
            viewMode: "3D",
            zoom: 18,
            center: this.mapLocation,
          });
        })
        .catch((e) => {
          console.error(e);
        });
    },

    openPayDialog(index) {
      this.form.roomindex = index
      console.log("here")
      this.payVisible = true;
    },

    initDatePicker() {
      const vm = this;
      let today = new Date();
      today.setHours(0, 0, 0, 0); // 将时间设置为今天的午夜，忽略小时和分钟

      flatpickr("#datepicker", {
        mode: "range",
        minDate: "today",
        onReady: function (selectedDates, dateStr, instance) {
          instance.calendarContainer.classList.add("form-calendar");
        },
        onDayCreate: function (dObj, dStr, fp, dayElem) {
          let date = dayElem.dateObj;
          date.setHours(0, 0, 0, 0); // 同样忽略小时和分钟

          let diff = (date - today) / (1000 * 60 * 60 * 24); // 计算差异天数
          if (diff >= 0 && diff < vm.hotelDetails.prices.length) {
            const price = vm.hotelDetails.prices[vm.form.roomindex * vm.hotelDetails.rooms.length + diff];
            const priceSpan = document.createElement("span");
            priceSpan.className = "date-price";
            priceSpan.textContent = "￥" + price;
            dayElem.appendChild(priceSpan);
          }
        },
        onChange: function (selectedDates) {
          console.log(selectedDates)
          if (selectedDates.length === 2) {
            let date = selectedDates[0];
            let userTimezoneOffset = date.getTimezoneOffset() * 60000;
            let adjustedDate = new Date(date.getTime() - userTimezoneOffset);
            let startDate = adjustedDate.toISOString().substring(0, 10);
            date = selectedDates[1];
            userTimezoneOffset = date.getTimezoneOffset() * 60000;
            adjustedDate = new Date(date.getTime() - userTimezoneOffset);
            let endDate = adjustedDate.toISOString().substring(0, 10)
            vm.form.date = [startDate, endDate];
          } else if (selectedDates.length === 1) {
            let date = selectedDates[0];
            let userTimezoneOffset = date.getTimezoneOffset() * 60000;
            let adjustedDate = new Date(date.getTime() - userTimezoneOffset);
            let startDate = adjustedDate.toISOString().substring(0, 10);
            vm.form.date = [startDate, startDate];
          }
          let storeDate = [new Date(vm.form.date[0]), new Date(vm.form.date[1])]
          console.log(storeDate)
          vm.$store.dispatch('search', { date: storeDate, city: vm.hotelDetails.city });
        }
      });
    },

    handleClose(done) {
      this.dialogVisible = false;
      done();
    },

    updateIdNumberFields(newRoomCount) {
      const currentCount = this.form.names.length;
      if (newRoomCount > currentCount) {
        for (let i = currentCount; i < newRoomCount; i++) {
          this.form.names.push('');
        }
      } else {
        this.form.names.splice(newRoomCount);
      }
    },

    paySuccess() {
      ElNotification({
        title: '支付成功',
        message: '订单已支付成功，享受你的旅途吧！',
        type: 'success',
        offset: 200,
      })
    },

    validateNames(rule, value, callback) {
      const hasEmptyName = value.some(name => !name.trim());
      if (hasEmptyName) {
        return callback(new Error('住客姓名不能为空'));
      } else {
        return callback();
      }
    },

    updateRemains() {
      let inday = new Date(this.form.date[0])
      let outday = new Date(this.form.date[1])
      let differenceInMilliseconds = outday - inday;
      let nights = differenceInMilliseconds / (1000 * 60 * 60 * 24)

      for (let i = 0; i < nights; i++) {
        let index = this.form.roomindex * this.hotelDetails.rooms.length + i;
        // 将字符串转换为整数，执行减法，然后转换回字符串
        this.hotelDetails.remains[index] = String(parseInt(this.hotelDetails.remains[index]) - this.form.rooms);
      }

      console.log(this.hotelDetails.remains)
    },

    onSubmit() {

      this.getForm = {
        id: "0125",
        hotelName: this.hotelDetails.name,
        roomNumber: '到酒店办理入住时确认',
        guestName: this.form.names.join(";"),
        price: this.computedTotalPrice,
        guestsCount: this.roomDetails[this.form.roomindex].numberOfHuman * this.form.rooms,
        checkInDate: this.form.date[0],
        checkOutDate: this.form.date[1],
        phoneNumber: this.form.phone,
        orderNote: this.form.orderNote,
        userId: this.$store.getters.user.id,
        status: "已支付（待入住）",
        roomType: this.roomDetails[this.form.roomindex].name
      }

      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$axios
            .post('http://localhost:8080/order/add', this.getForm)
            .then(() => {
              this.updateRemains()
              this.getForm = {
                name: this.hotelDetails.name,
                remains: this.hotelDetails.remains
              }
              this.paySuccess()
              this.payVisible = false; // 关闭对话框
            })
            .catch((error) => {
              console.error('Error fetching hotel details:', error);
            });
        } else {
          // 如果验证失败，显示错误提示
          console.log('表单验证失败');
          return false;
        }
      });
    }
  },

  watch: {
    'form.rooms'(newVal) {
      this.updateIdNumberFields(newVal);
    }
  },

  mounted() {
    console.log("mounted")
    window.addEventListener('scroll', this.onScroll);

    let today = new Date();
    today.setHours(0, 0, 0, 0);

    this.fp = flatpickr("#live-time", {
      mode: "range",
      minDate: "today",
      onClose: function (selectedDates) {
        // 处理日期选择
        console.log(selectedDates);
      },
      defaultDate: [this.startDate, this.endDate],
      onChange: this.onDateChange,
    });

    console.log(this.fp);

  },

  beforeDestroy() {
    window.removeEventListener('scroll', this.onScroll);
    map.destroy();
  },
}
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: auto;
}

.card {
  width: auto;
  border: 1px outset rgba(119, 119, 119, 0.25);
  /* 设置黑色边框 */
  border-radius: 5px;
  /* 可选：添加边框圆角 */
  margin-top: 10px;
}

.search-form {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 65px;
  width: 98vw;
}

.city-input,
.time-input {
  height: 45px;
}

.city-input {
  width: 20vw;
  left: -10.5vw;
}

.date-picker-button {
  position: relative;
  left: -3vw;
  height: 45px;
  width: 11vw;
  display: flex;
  flex-direction: row;
  border-radius: 20px;
}

.date-info {
  display: flex;
  flex-direction: column;
  margin-left: 6px;
}

.selected-date {
  font-weight: bold;
  font-size: 15px;
}

.selected-day {
  font-size: 15px;
  color: grey;
}

.search-button {
  height: 50px;
  width: 120px;
  position: relative;
  left: 10vw;
  background-color: #409EFF;
  color: white;
}

.hotel-images {
  display: flex;
  flex-direction: row;
  margin-top: 5vh;
  width: auto;
}

.hotel-top-image {
  width: 28vw;
  height: 39vh;
  display: block;
  object-fit: cover;
}

.hotel-images-two {
  display: flex;
  flex-direction: column;
  margin-left: 1vw;
}

.hotel-image {
  width: 13.5vw;
  height: 19vh;
  display: block;
  margin-bottom: 1vh;
  object-fit: cover;
}

.nav-menu {
  display: flex;
  flex-direction: row;
  width: 72vw;
  margin-top: 0px;
}

.nav-menu.affix-active {
  width: 100vw;
  left: -14.5vw;
  padding-left: 14.5vw;
}

.el-menu-item {
  width: auto;
}

.el-menu-item:hover {
  color: #409EFF;
  /* 设置字体颜色为蓝色 */
  border-bottom: 5px solid #409EFF;
  /* 底部添加蓝色边框 */
  background: transparent;
}

.el-menu-item.is-active {
  color: #409EFF;
  /* 激活时的字体颜色 */
  border-bottom: 5px solid #409EFF;
  /* 激活时底部的边框 */
}

.introduction {
  margin-top: 20px;
  left: -0.8vw;
  width: 50vw;
  padding: 20px;
  position: relative;
}

.introduction .hotel-name {
  position: relative;
  font-size: 30px;
  font-weight: bold;
}

.star {
  width: 1vw;
}

.line {
  border-top: 1px solid rgba(73, 72, 72, 0.3);
}

.intro-container {
  display: flex;
  flex-direction: row;
}


.introduction .hotel-location {
  position: relative;
  margin-top: 1vh;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.8)
}

.introduction .hotel-intro {
  position: relative;
  margin-top: 1vh;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.5)
}

.facility {
  margin-top: 2vh;
  left: -0.8vw;
  width: 50vw;
  padding: 20px;
  position: relative;
}

.facility .facility-name {
  font-size: 20px;
}

.facilities {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin-top: 20px;
}

.facility-content {
  flex: 1;
  text-align: center;
}

.fac-content-name {
  margin-top: 2px;
  font-size: 12px;
}

.rate {
  margin-top: 2.7vh;
  left: 0.7vw;
  position: relative;
  width: 18vw;
}

.rate-header .score {
  font-weight: bold;
  font-size: 20px;
  margin-top: 20px;
  margin-left: 20px;
}

.rate-header .rate-num {
  margin-top: 5px;
  margin-left: 20px;
}

.review-card {
  margin-top: 5px;
  margin-left: 10px;
  margin-right: 10px;
  overflow: hidden;
  border-radius: 4px;
  border: 1px solid rgba(73, 72, 72, 0.3);
}

.review-content {
  background-color: #f5f5f5;
  padding: 10px;
  border-radius: 4px 4px 0 0;
  font-size: 12px;
}

.reviewer-name {
  padding: 5px;
  text-align: center;
  background-color: #ffffff;
  margin: 0;
  border-radius: 0 0 4px 4px;
  font-size: 13px;
}

.map-card {
  display: flex;
  left: 0.7vw;
  position: relative;
  width: 18vw;
  height: 17.5vh;
  justify-items: center;
}

.map-image {
  position: relative;
  width: 16vw;
  height: 16.5vh;
  left: 1vw;
  top: 0.5vh;
  border-radius: 5px;
  cursor: pointer;
}

#dia-map {
  width: 65vw;
  height: 65vh;
}

.rooms {
  width: 72vw;
  padding-bottom: 20px;
}

.room-detail {
  display: flex;
  position: relative;
  flex-direction: row;
  justify-content: space-between;
}

.room-name {
  margin-top: 2vh;
  margin-left: 1vw;
  font-size: 20px;
}

.room-image {
  margin-top: 2vh;
  margin-left: 1vw;
}

.room-information {
  margin-left: 5vw;
}

.room-inf-text {
  position: relative;
  margin-left: 0.5vw;
  font-size: 15px;
  top: 3px;
}

.room-discount {
  margin-left: 5vw;
}

.room-price-pay {
  margin-left: 5vw;
}

.room-price {
  position: relative;
  font-size: 25px;
  font-weight: bold;
  color: orange
}

.room-pay {
  background-color: orange;
  width: 8vw;
  height: 6vh;
  font-size: 20px;
  margin-top: 1vh;
  color: white;
}

.room-quick {
  margin-top: 5vh;
  color: red;
  transform: rotate(-5deg);
}

.form-calendar .date-price {
  font-size: 12px;
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  white-space: nowrap;
  color: orange;
}


.form-calendar .flatpickr-day {
  position: relative;
  padding-bottom: 60px;
}
</style>
