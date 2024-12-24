<template>
	<div>
		<canvas id="canvas" style="background:url(http://clfile.zggen.cn/20231110/c5e451b550874e58af90e0ad1a95641e.jpg)"></canvas>
		<div class="register_view">
			<div class="outTitle_view">
				<div class="outTilte">{{projectName}}注册</div>
			</div>
			<el-form :model="registerForm" class="register_form">
				<div class="list_item">
					<div class="list_label">用户账号：</div>
					<input class="list_inp"
					 v-model="registerForm.yonghuzhanghao" 
					 placeholder="请输入用户账号"
					 type="text"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">用户密码：</div>
					<input class="list_inp"
					 v-model="registerForm.yonghumima" 
					 placeholder="请输入用户密码"
					 type="password"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">确认用户密码：</div>
					<input class="list_inp" v-model="registerForm.yonghumima2" type="password" placeholder="请输入确认用户密码" />
				</div>
				<div class="list_item">
					<div class="list_label">用户姓名：</div>
					<input class="list_inp"
					 v-model="registerForm.yonghuxingming" 
					 placeholder="请输入用户姓名"
					 type="text"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">头像：</div>
					<div class="list_file_list">
						<uploads
							action="file/upload" 
							tip="请上传头像" 
							:limit="3" 
							style="width: 100%;text-align: left;"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''" 
							@change="touxiangUploadSuccess">
						</uploads>
					</div>
				</div>
				<div class="list_item">
					<div class="list_label">性别：</div>
					<el-select 
						class="list_sel" 
						v-model="registerForm.xingbie" 
						placeholder="请选择性别"
						>
						<el-option v-for="item in yonghuxingbieLists" :label="item" :value="item"></el-option>
					</el-select>
				</div>
				<div class="list_item">
					<div class="list_label">手机号码：</div>
					<input class="list_inp"
					 v-model="registerForm.shoujihaoma" 
					 placeholder="请输入手机号码"
					 type="text"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">邮箱：</div>
					<input class="list_inp" v-model="registerForm.email" placeholder="邮箱" />
				</div>
				<div class="list_code">
					<div class="list_code_label">验证码：</div>
					<div class="list_code_item">
						<input class="list_code_inp" v-model="emailCode" placeholder="请输入验证码" />
						<el-button class="list_code_btn" :disabled="getCodeType" :type="getCodeType?'info':'primary'" @click="getEmailCode">{{setCode}}</el-button>
					</div>
				</div>
				<div class="list_item">
					<div class="list_label">密保问题：</div>
					<input class="list_inp"
					 v-model="registerForm.pquestion" 
					 placeholder="请输入密保问题"
					 type="text"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">密保答案：</div>
					<input class="list_inp"
					 v-model="registerForm.panswer" 
					 placeholder="请输入密保答案"
					 type="text"
					 />
				</div>
				<div class="list_btn">
					<el-button class="register" type="success" @click="handleRegister">注册</el-button>
					<div class="r-login" @click="close">已有账号，直接登录</div>
				</div>
			</el-form>	
		</div>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onUnmounted,
		onMounted,
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context?.$project.projectName
	//动态背景
	import canvasBg from "@/assets/js/canvas-bg-4.js";
	import "@/assets/css/canvas-bg-4.css"
	onMounted(()=>{
		canvasBg()
	})
	// onUnmounted(()=>{
	// 	nextTick(()=>{
	// 		canvasBg = null
	// 	})
	// })
	//获取注册类型
	import { useRoute } from 'vue-router';
	const route = useRoute()
	const tableName = ref('yonghu')
	
	//公共方法
	const getUUID=()=> {
		return new Date().getTime();
	}
	
	const registerForm = ref({
        xingbie: '',
	})
	//验证码
	const time = ref(0)
	const setCode = ref('发送验证码')
	const getCodeType = ref(false)
	const timer=()=>{
		if(time.value>0){
			getCodeType.value = true
			setCode.value = time.value + 's'
			time.value--
			setTimeout(()=>{
				timer()
			},1000)
		}else{
			time.value = 0
			setCode.value = '发送验证码'
			getCodeType.value = false
		}
	}
	//邮箱验证码
	const emailCode = ref('')
	const getEmailCode=()=>{
		if((!registerForm.value.email)){
			context?.$toolUtil.message(`邮箱不能为空`,'error')
			return false
		}
		if(!context?.$toolUtil.isEmail(registerForm.value.email)){
			context?.$toolUtil.message(`邮箱格式不正确`,'error')
			return false
		}
		time.value = 60
		timer()
		context?.$http({
			url:`${tableName.value}/sendemail?email=`+registerForm.value.email,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.message(`发送成功`,'success')
		})
	}
	const yonghuxingbieLists = ref([])
	const init=()=>{
		yonghuxingbieLists.value = "男,女".split(',')
	}
    const touxiangUploadSuccess=(fileUrls)=> {
        registerForm.value.touxiang = fileUrls;
    }
	// 多级联动参数
	//注册按钮
	const handleRegister = () => {
		let url = tableName.value +"/register";
		if((!registerForm.value.yonghuzhanghao)){
			context?.$toolUtil.message(`用户账号不能为空`,'error')
			return false
		}
		if((!registerForm.value.yonghumima)){
			context?.$toolUtil.message(`用户密码不能为空`,'error')
			return false
		}
		if(registerForm.value.yonghumima!=registerForm.value.yonghumima2){
			context?.$toolUtil.message('两次密码输入不一致','error')
			return false
		}
		if((!registerForm.value.yonghuxingming)){
			context?.$toolUtil.message(`用户姓名不能为空`,'error')
			return false
		}
		if(registerForm.value.touxiang!=null){
			registerForm.value.touxiang = registerForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(registerForm.value.shoujihaoma&&(!context?.$toolUtil.isMobile(registerForm.value.shoujihaoma))){
			context?.$toolUtil.message(`手机号码应输入手机格式`,'error')
			return false
		}
		if(registerForm.value.email&&(!context?.$toolUtil.isEmail(registerForm.value.email))){
			context?.$toolUtil.message(`邮箱应输入邮件格式`,'error')
			return false
		}
			url=tableName.value +"/register?emailcode="+emailCode.value;
			if(registerForm.value.email&&(!context?.$toolUtil.isEmail(registerForm.value.email))){
				context?.$toolUtil.message('请输入正确的邮箱格式','error')
				return false
			}
		if((!emailCode.value)){
			context?.$toolUtil.message('验证码不能为空','error')
			return false
		}
		
		context?.$http({
			url:url,
			method:'post',
			data:registerForm.value
		}).then(res=>{
			context?.$toolUtil.message('注册成功','success', obj=>{
				context?.$router.push({
					path: "/login"
				});
			})
		})
	}
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
	init()
</script>
<style lang="scss" scoped>
	.register_view {
		background: none !important;
		display: flex;
		align-items: center;
		justify-content: center;
		min-height: 100vh;
		position: relative;
		flex-direction: column;
		// 标题盒子
		.outTitle_view {
			padding: 0px;
			margin: 20px 0 10px;
			display: flex;
			align-items: center;
			.outTilte {
				border: 0px solid #fff;
				border-radius: 0px;
				padding: 10px 20px;
				color: #fff;
				font-size: 30px;
			}
		}
		// 表单盒子
		.register_form {
			border-radius: 4px;
			padding: 0px 90px 40px 40px;
			margin: 0;
			background: rgba(0,0,0,.0);
			display: block;
			width: 600px;
			justify-content: center;
			flex-wrap: wrap;
		}
		// item盒子
		.list_item {
			margin: 0 0 12px;
			display: flex;
			width: 100%;
			justify-content: flex-start;
			align-items: center;
			// label
			.list_label {
				color: #fff;
				width: 120px;
				font-size: 14px;
				box-sizing: border-box;
				text-align: right;
			}
			// 输入框
			:deep(.list_inp) {
				border: 1px solid #ddd;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: #fff;
				width: calc(100% - 100px);
				border-width: 0 0 1px;
				line-height: 36px;
				box-sizing: border-box;
				height: 36px;
				//去掉默认样式
				.el-input__wrapper{
					border: none;
					box-shadow: none;
					background: none;
					border-radius: 0;
					height: 100%;
					padding: 0;
				}
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
		// 验证码盒子
		.list_code {
			margin: 10px 0;
			display: flex;
			width: 100%;
			justify-content: flex-start;
			align-items: center;
			// 验证码label
			.list_code_label {
				color: #fff;
				width: 120px;
				font-size: 14px;
				box-sizing: border-box;
				text-align: right;
			}
			// 验证码item
			.list_code_item {
					display: flex;
					width: calc(100% - 120px);
					justify-content: space-between;
					align-items: center;
				// 输入框
				:deep(.list_code_inp) {
					border: 1px solid #ddd;
					border-radius: 0px 0 0 0px;
					padding: 0 10px;
					color: #333;
					background: #fff;
					width: 100%;
					border-width: 0 0 1px;
					line-height: 36px;
					box-sizing: border-box;
					height: 36px;
					//去掉默认样式
					.el-input__wrapper{
						border: none;
						box-shadow: none;
						background: none;
						border-radius: 0;
						height: 100%;
						padding: 0;
					}
					.is-focus {
						box-shadow: none !important;
					}
				}
				// 验证码按钮
				.list_code_btn {
					border: 0;
					border-radius: 0 0px 0px 0;
					padding: 0;
					margin: 0 0 0 10px;
					background: #113961;
					width: 120px;
					line-height: 36px;
					height: 36px;
				}
			}
		}
		//下拉框样式
		:deep(.list_sel) {
			border: 1px solid #ddd;
			border-radius: 0px;
			padding: 0 10px;
			color: #666;
			background: #fff;
			width: calc(100% - 100px);
			border-width: 0 0 1px;
			line-height: 36px;
			box-sizing: border-box;
			//去掉默认样式
			.select-trigger{
				height: 100%;
				.el-input{
					height: 100%;
					.el-input__wrapper{
						border: none;
						box-shadow: none;
						background: none;
						border-radius: 0;
						height: 100%;
						padding: 0;
					}
					.is-focus {
						box-shadow: none !important;
					}
				}
			}
		}
		//图片上传样式
		.list_file_list  {
			//提示语
			:deep(.el-upload__tip){
				margin: 7px 0 0;
				color: #666;
				display: flex;
				font-size: 14px;
				justify-content: flex-start;
				align-items: center;
			}
			//外部盒子
			:deep(.el-upload--picture-card){
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 120px;
				line-height: 48px;
				text-align: center;
				height: 44px;
				//图标
				.el-icon{
					color: #999;
					font-weight: 600;
					font-size: 24px;
				}
			}
			:deep(.el-upload-list__item) {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 120px;
				line-height: 48px;
				text-align: center;
				height: 44px;
			}
		}
		//按钮盒子
		.list_btn {
			margin: 20px 0 0;
			display: flex;
			width: 100%;
			justify-content: flex-end;
			align-items: center;
			flex-wrap: wrap;
			//注册按钮
			.register {
					border: none;
					border-radius: 0px;
					color: #fff;
					background: #113961;
					width: calc(100% - 120px);
					font-size: 16px;
					height: 40px;
			}
			//注册按钮悬浮样式
			.register:hover {
				border: none;
				border-radius: 4px;
				background: rgba(45,160,100,1);
				width: calc(100% - 120px);
				font-size: 16px;
				opacity: 1;
				height: 40px;
			}
			//已有账号
			.r-login {
				cursor: pointer;
				padding: 20px 0 0 80px;
				color: #eee;
				width: 80%;
				font-size: 14px;
				text-align: right;
			}
		}
	}
</style>