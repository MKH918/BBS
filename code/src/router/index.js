import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import forumList from '@/views/pages/forum/list'
import forumstoreList from '@/views/pages/forumstore/list'
import forumstoreDetail from '@/views/pages/forumstore/formModel'
import forumstoreAdd from '@/views/pages/forumstore/formAdd'
import emailregistercodeList from '@/views/pages/emailregistercode/list'
import emailregistercodeDetail from '@/views/pages/emailregistercode/formModel'
import emailregistercodeAdd from '@/views/pages/emailregistercode/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'forumstoreList',
			component: forumstoreList
		}, {
			path: 'forumstoreDetail',
			component: forumstoreDetail
		}, {
			path: 'forumstoreAdd',
			component: forumstoreAdd
		}
		, {
			path: 'emailregistercodeList',
			component: emailregistercodeList
		}, {
			path: 'emailregistercodeDetail',
			component: emailregistercodeDetail
		}, {
			path: 'emailregistercodeAdd',
			component: emailregistercodeAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
	,{
		path: '/forget',
		name: 'forget',
		component: () => import('../views/pages/forget.vue')
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
