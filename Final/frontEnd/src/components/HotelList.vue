<template>
  <div>
    <div class="fixedSearch">
      <el-affix :offset="60">
        <el-row style="background-color: rgb(255, 255, 255);">
          <el-col :span="12">
            <el-form class="formDetail" ref="form" :model="form" style="margin-top: 10px; margin-left: 90px;">
              <el-form-item style="">
                <el-date-picker v-model="form.date" type="daterange" range-separator="至" start-placeholder="开始日期"
                  end-placeholder="结束" format="YYYY/MM/DD" :size="size" />
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="12">
            <el-select v-model="form.value" placeholder="请选择" style="margin-left: 325px; margin-top: 10px;">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
      </el-affix>
    </div>
    <div>
      <div v-for="hotel in hotels" style="margin-left: 320px;" v-loading="loading" element-loading-text="拼命加载中"
        :key="hotel.name" class="hotel-item" @click="goToDetail(hotel.name)">
        <div class="hotel-image">
          <img :src=hotel.view>
        </div>
        <div class="hotel-details">
          <p class="hotel-name">{{ hotel.name }}</p>
          <el-row>
            <el-col :span="20">
              <p class="hotel-introduction"> {{ hotel.introduction }}</p>
            </el-col>
            <el-col :span="4">
              <p class="hotel-price">{{ hotel.price }} ￥</p>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'HotelList',
  data() {
    return {
      loading: false,
      options: [{
        value: '选项1',
        label: '默认排序'
      }, {
        value: '选项2',
        label: '离我最近'
      }, {
        value: '选项3',
        label: '低价优先'
      }, {
        value: '选项4',
        label: '高价优先'
      }],
      form: {
        city: this.$store.getters.city,
        date: this.$store.getters.date,
        value: '选项1'
      },
      searchForm: {
        checkIn: '',
        checkOut: '',
        city: ''
      },
      hotels: []
    }
  },
  watch: {
    form: {
      handler: function (newForm) {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
        }, 500);
        this.updateSearchForm();
        this.$store.dispatch('search', { date: this.form.date, city: this.form.city });
        this.fetchHotelData();
      },
      deep: true,
    },
  },
  mounted() {
    const form = JSON.parse(this.$route.query.form)
    this.form.date = form.date;
    this.form.city = form.name;
    this.searchForm = {
      city: form.name,
      checkIn: form.date[0],
      checkOut: form.date[1]
    };
    console.log("searchform")
    console.log(this.searchForm)
    this.fetchHotelData();
  },
  methods: {
    updateSearchForm() {
      this.searchForm = {
        city: this.form.city,
        checkIn: this.form.date[0],
        checkOut: this.form.date[1]
      };
    },
    fetchHotelData() {
      this.$axios.post('http://localhost:8080/hotel/search', this.searchForm)
        .then(response => {
          this.hotels = response.data.data;
          this.sortHotels();
        })
        .catch(error => {
        });
    },
    sortHotels() {
      switch (this.form.value) {
        case '选项2': // 离我最近
          this.hotels.sort((a, b) => { return b.name.localeCompare(a.name); });
          break;
        case '选项3': // 低价优先
          this.hotels.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
          break;
        case '选项4': // 高价优先
          this.hotels.sort((a, b) => parseFloat(b.price) - parseFloat(a.price));
          break;
        default: // 默认选项
          break;
      }
    },
    goToDetail(name) {
      let storeDate = [new Date(this.form.date[0]), new Date(this.form.date[1])]
      this.$store.dispatch('search', { date: storeDate, city: this.form.city });
      this.$router.push({ name: 'Detail', params: { a: name } });
    },
  }
}
</script>

<style scoped>
.fixedSearch {
  display: flex;
  position: relative;
  justify-content: space-around;
  margin-top: 20px;
  margin-left: 40px;
  width: 90vw;
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