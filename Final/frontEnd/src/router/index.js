import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '@/components/HelloWorld.vue'
import Home from '@/components/Home.vue'
import Detail from '@/components/Detail.vue'
import Order from '@/components/Order.vue'
import User from '@/components/User.vue'
import Login from '@/components/Login.vue'
import Registry from '@/components/Registry.vue'
import OrderDetail from '@/components/OrderDetail.vue'
import Pay from '@/components/Pay.vue'
import HotelList from '@/components/HotelList.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      redirect: 'Home',
      children: [
        {
          path: 'Home',
          name: 'Home',
          component: Home
        },
        {
          path: 'Order',
          name: 'Order',
          component: Order
        },
        {
          path: '/orders/:id',
          name: 'OrderDetail',
          component: OrderDetail
        },
        {
          path: 'HotelList',
          name: 'HotelList',
          component: HotelList
        }, {
          path: '/User',
          name: 'User',
          component: User,
        },
      ]
    },
    {
      path: '/Detail/:a',
      name: 'Detail',
      component: Detail
    },

    {
      path: '/Login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/Registry',
      name: 'Registry',
      component: Registry
    },
    {
      path: '/Pay',
      name: 'Pay',
      component: Pay
    },

  ]
})

export default router
