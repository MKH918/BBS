	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import forum from '@/views/forum/list'
	import news from '@/views/news/list'
	import yonghu from '@/views/yonghu/list'
	import forumstore from '@/views/forumstore/list'
	import config from '@/views/config/list'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/news',
			name: '新闻资讯',
			component: news
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/forumstore',
			name: '我的收藏',
			component: forumstore
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
