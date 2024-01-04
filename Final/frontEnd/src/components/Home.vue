<template>
  <div class="home">
    <div class="formTable">
      <div class="TextofLogo">
        <p class="TextTitle">寻找下一程住宿</p>
        <p class="TextContent">酒店、民宿……更多住宿优惠搜不停</p>
      </div>
      <el-form class="formDetail" ref="form" :model="form" label-width="80px">
        <el-form-item style="margin-top: auto" prop="name" :rules="nameRules">
          <el-autocomplete style="width: 400px;" placeholder="请选择您想去的城市" prefix-icon="search" v-model="form.name"
            :fetch-suggestions="querySearch" clearable class="inline-input w-50" @select="handleSelect">
          </el-autocomplete>
        </el-form-item>
        <el-form-item style="margin-top: auto" prop="date" :rules="dateRules">
          <el-date-picker v-model="form.date" type="daterange" range-separator="至" start-placeholder="请选择入住日期"
            end-placeholder="离店日期" format="YYYY/MM/DD" :picker-options="pickerOptions" :size="large" />
        </el-form-item>
        <el-form-item style="margin-top: auto;">
          <el-button style="background-color: cadetblue; border: 1px solid cadetblue; width: 140px;" type="primary"
            @click="onSubmit">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="card" v-loading="loading" element-loading-text="拼命加载中">
      <el-carousel :interval="2000" type="card" indicator-position="none" arrow="never" height="300px " width="300px">
        <el-carousel-item v-for="hotel in hotels.slice(2, 5)" :key="hotel.name" @click="goToDetail(hotel.name)">
          <img :src="hotel.view" alt="Hotel Image" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="recommend" style="position: sticky; top: 0; z-index: 100; background-color: rgb(255, 255, 255);">
      <el-row>
        <el-col :span="6">
          <p style="font-size: 35px; color: rgb(0, 0, 0); margin-left: 80px;">酒店推荐</p>
        </el-col>
        <el-col :span="18">
          <el-tabs style="margin-top: 35px; margin-left: 0px; padding-left: 0px;" v-model="activeName"
            @tab-click="handleClick">
            <el-tab-pane label="南京" name="first"></el-tab-pane>
            <el-tab-pane label="上海" name="second"></el-tab-pane>
            <el-tab-pane label="北京" name="fourth"></el-tab-pane>
            <el-tab-pane label="三亚" name="sixth"></el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
    <el-row>
      <el-col :span="4">
        <el-carousel :interval="2000" arrow="never" indicator-position="none">
          <el-carousel-item>
            <div v-show="dialogVisible" style="position: relative;">
              <img src="@/assets/snow1.jpg" class="img_ad" alt="Advertisement" @click="redirectToMaFengWo"
                style="cursor: pointer;">
              <button @click="deleteImage" class="delete-button"
                style="margin-top: 20px; position: absolute; top: 0; right: 0; cursor: pointer; ">关闭</button>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <div v-show="dialogVisible" style="position: relative;">
              <img src="@/assets/snow2.jpg" class="img_ad" alt="Another Advertisement" @click="redirectToMaFengWo"
                style="cursor: pointer;">
              <button @click="deleteImage" class="delete-button"
                style="margin-top: 20px; position: absolute; top: 0; right: 0; cursor: pointer;">关闭</button>
            </div>
          </el-carousel-item>
        </el-carousel>
      </el-col>
      <el-col :span="17">
        <div v-loading="loading" element-loading-text="拼命加载中">
          <div v-for="hotel in hotels" style="margin-left: 70px; cursor: pointer;" :key="hotel.name" class="hotel-item"
            @click="goToDetail(hotel.name)">
            <div class="hotel-image">
              <img :src=hotel.view alt="Hotel Image" />
            </div>
            <div class="hotel-details">
              <p class="hotel-name">{{ hotel.name }}</p>
              <el-row>
                <el-col :span="20">
                  <p class="hotel-introduction">{{ hotel.introduction }}</p>
                </el-col>
                <el-col :span="4">
                  <p class="hotel-price">{{ hotel.price }} ￥</p>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="1">
        <div v-show="dialogVisible1" style="position: relative;">
          <img src="@/assets/mcdonalds.png" class="img_ad2" alt="Advertisement" @click="redirectToMaFengWo1"
            style="cursor: pointer; padding-right: 5px;">
          <button @click="deleteImage1" class="delete-button"
            style="margin-top: 20px; position: absolute; top: 0; left: 0;cursor: pointer;">关闭</button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      loading: false,
      dialogVisible: true,
      dialogVisible1: true,
      cityes: [],
      recommendForm: {
        city: '南京'
      },
      activeName: 'first',
      hotels: [],
      form: {
        name: this.$store.getters.city,
        date: this.$store.getters.date
      },
      nameRules: [
        { required: true, message: '请选择目的地', trigger: 'change' }
      ],
      dateRules: [
        { required: true, message: '请选择入住/离店日期', trigger: 'change' }
      ]
    }
  },
  created() {
    this.fetchAndSetCityData();
    this.$axios.post('http://localhost:8080/hotel/hotels', this.recommendForm)
      .then(response => {
        this.hotels = response.data.data;
        console.log('hotels', this.hotels)
      })
      .catch(error => {
      });
  },
  methods: {
    deleteImage() {
      this.dialogVisible = false;
    },
    redirectToMaFengWo() {
      window.location.href = 'http://www.hrbicesnow.com/';
    },
    deleteImage1() {
      this.dialogVisible1 = false;
    },
    redirectToMaFengWo1() {
      window.location.href = 'https://www.mcdonalds.com.cn/page/offical-channels/';
    },
    async handleClick(tab, event) {
      this.loading = true;
      const cityName = tab.props.label;
      this.recommendForm.city = cityName;
      try {
        const response = await this.$axios.post('http://localhost:8080/hotel/hotels', this.recommendForm);
        this.hotels = response.data.data;
      } catch (error) {
      } finally {
        setTimeout(() => {
          this.loading = false;
        }, 1000);
      }
    },
    async fetchCityData() {
      try {
        const response = await this.$axios.get('http://localhost:8080/city/getallcitys');
        return response.data.data;
      } catch (error) {
        return [];
      }
    },
    async fetchAndSetCityData() {
      const cityData = await this.fetchCityData();
      this.cities = cityData;
    },
    async querySearch(queryString, cb) {
      if (queryString === '') {
        cb(this.cities);
      } else {
        const results = this.cities.filter(this.createFilter(queryString));
        cb(results);
      }
    },
    createFilter(queryString) {
      return city => {
        return city.value.toLowerCase().includes(queryString.toLowerCase());
      };
    },
    onSubmit() {
      let self = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
          let storeDate = [new Date(this.form.date[0]), new Date(this.form.date[1])]
          this.$store.dispatch('search', { date: storeDate, city: this.form.name });
          this.$router.push({
            name: 'HotelList',
            query: {
              form: JSON.stringify(self.form)
            }
          });
        }
      });
    },
    goToDetail(name) {
      let storeDate = [new Date(this.form.date[0]), new Date(this.form.date[1])]
      this.$store.dispatch('search', { date: storeDate, city: this.form.name });
      this.$router.push({ name: 'Detail', params: { a: name } });
    },
  }
}
</script>

<style scoped>
.home {
  margin: 0;
  padding: 0;
}

.formTable {
  height: 210px;
  padding: 40px;
  padding-left: 40vh;
  background-color: rgb(16, 113, 111);
  text-align: start;
}


.img_ad {
  height: 350px;
  width: 180px;
  margin-top: 20px;
  margin-left: 70px;
}

.img_ad2 {
  height: 350px;
  width: 180px;
  margin-top: 20px;
}

.TextTitle {
  color: rgb(251, 251, 251);
  font-size: 24px;
}

.TextContent {
  color: rgb(251, 251, 251);
  font-size: 16px;
}

.formDetail {
  display: flex;
  align-items: center;
  flex-direction: column;
  position: relative;
  top: 3vh;
  left: 7vw;
  width: 40vw;
  height: 20vh;
  border: 1px solid #ffffff;
  background-color: rgb(254, 254, 254);
  border-top-left-radius: 30%;
  padding-left: 0px;
  padding-top: 42px;
  padding-right: 70px;
}

.card {
  margin-top: 200px;
  margin-left: 200px;
  margin-right: 0px;
  border: 0;
  text-align: start;
}

.recommend {
  margin-top: 50px;
  text-align: start;
}

.el-tabs.is-active {
  color: orange;
  background-color: orange;
}

.hotel-item {
  display: flex;
  width: 900px;
  margin-bottom: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 20px;
}

.hotel-image {
  margin-right: 10px;
  margin-left: 20px;
  margin-top: 10px;
  margin-bottom: 5px;
}

.hotel-image img {
  width: 190px;
  height: 95px;
  border-radius: 3%;
}

.hotel-details {
  text-align: start;
  flex: 3;
}

.hotel-name {
  font-size: 20px;
  margin-left: 10px;
  font-weight: bold;
}

.hotel-introduction {
  margin-left: 10px;
  font-size: 14px;
  max-lines: 2;
}

.hotel-price {
  font-size: 16px;
  color: brown;
}
</style>