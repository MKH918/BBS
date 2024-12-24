const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '新闻资讯管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'新闻资讯',
							url:'/index/newsList'
						},
					]
				},
				{
					name: '论坛交流',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "基于java的BBS论坛系统"
        } 
    }
}
export default config
