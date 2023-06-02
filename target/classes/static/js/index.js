$(function(){
	// 自定义滚动条
	$('.content_list').mCustomScrollbar()
	
	var $audio = $('audio');
	var player = new Player($audio);
	var progress;
	var voiceProgress;
	var lyric;
	// 加载歌曲列表的方法
	function getPlayerList(){
		$.ajax({
			url:"/song",
			type: "get",
			dataType: "json",
			contentType: "application/json;charset=UTF-8",
			success:function(data){
				// console.log(data.data)
				player.musicList = data.data
				var $musicList = $('.content_list ul')
				$.each(data.data,function(index,ele){
					var $item = createMusicItem(index,ele)
					$musicList.append($item)
				})
				inintMusicInfo(data.data[0])
				initMusicLyric(data.data[0])
			},
			error:function(e){
				console.log(e)
			}
		})
	}
	// 初始化歌曲信息
	function inintMusicInfo(music){
		var $musicImage = $('.song_info_pic img')
		var $musicName = $('.song_info_name a')
		var $musicSinger = $('.song_info_singer a')
		var $musicAblum = $('.song_info_ablum a')
		var $musicPrograssName = $('.music_progress_name')
		var $musicPrograssTime = $('.music_progress_time')
		var $musicBg = $('.mask_bg')


		$musicImage.attr('src',"data:image/jpeg;base64,"+music.musicpic)
		$musicName.text(music.musicname)
		$musicSinger.text(music.singerid)
		// $musicAblum.text(music.album)
		$musicPrograssName.text(music.musicname+" / "+music.singerid)
		$musicPrograssTime.text("00:00 / "+music.musictime)
		$musicBg.css('background',`url(${music.cover})`)
	}
	// 初始化歌词信息
	function initMusicLyric(music){
		lyric = new Lyric(music.musicword)
		var $lryicContainer = $('.song_lyric')
		$lryicContainer.html('')
		lyric.loadLyric(function(){
			$.each(lyric.lyrics,function(index,ele){
				var $item = $('<li>'+ele+'</li>')
				$lryicContainer.append($item)
			})
		})
	}
	// 初始化进度条
	initProgress()
	function initProgress(){
		var $progressBar = $('.music_progress_bar')
		var $progressLine = $('.music_progress_line')
		var $progressDot = $('.music_progress_dot')
		progress = Progress($progressBar,$progressLine,$progressDot)
		progress.progressClick(function(value){
			player.musicSeekTo(value)
		})
		progress.progressMove(function(value){
			player.musicSeekTo(value)
		})
		
		var $voiceBar = $('.music_voice_bar')
		var $voiceLine = $('.music_voice_line')
		var $voiceDot = $('.music_voice_dot')
		voiceProgress = Progress($voiceBar,$voiceLine,$voiceDot)
		voiceProgress.progressClick(function(value){
			player.musicVoiceSeekTo(value)
		})
		voiceProgress.progressMove(function(value){
			player.musicVoiceSeekTo(value)
		})
	}
	// 初始化事件监听
	initEvents()
	function initEvents(){
		// 监听歌曲移入移出事件
		$('.content_list').delegate('.list_music','mouseenter',function(){
			$(this).find('.list_menu').stop().fadeIn(100)
			$(this).find('.list_time a').stop().fadeIn(100)
			$(this).find('.list_time span').stop().fadeOut(100)
		})
		$('.content_list').delegate('.list_music','mouseleave',function(){
			$(this).find('.list_menu').stop().fadeOut(100)
			$(this).find('.list_time a').stop().fadeOut(100)
			$(this).find('.list_time span').stop().fadeIn(100)
		})
		// 监听复选框点击事件
		$('.content_list').delegate('.list_check','click',function(){
			$(this).toggleClass('list_checked')
		})
		// 添加子菜单播放按钮的监听
		var $musicPlay = $('.music_play')
		$('.content_list').delegate('.list_menu_play','click',function(){
			var $item = $(this).parents('.list_music')
			$(this).toggleClass('list_menu_play2')
			$item.siblings().find('.list_menu_play').removeClass('list_menu_play2')
			if($(this).attr('class').indexOf('list_menu_play2')!= -1){
				$musicPlay.addClass('music_play2')
				$audio.src =
				$item.find('div').css('color','#fff')
				$item.siblings().find('div').css('color','rgba(255,255,255,0.5)')
			}else{
				$musicPlay.removeClass('music_play2')
				$item.find('div').css('color','rgba(255,255,255,0.5)')
			}
			$item.find('.list_number').toggleClass('list_number2')
			$item.siblings().find('.list_number').removeClass('list_number2')
		
			// 播放音乐
			// $audio.attr('src',"data:audio/mpeg;base64,"+$item.get(0).music.musicfile)
			player.playMusic($item.get(0).index,$item.get(0).music)
			
			// 切换歌曲信息
			inintMusicInfo($item.get(0).music)
			// 切换歌词信息
			initMusicLyric($item.get(0).music)
		})
		// 监听底部控制区域播放按钮点击
		$musicPlay.click(function(){
			if(player.currentIndex == -1){
				$('.list_music').eq(0).find('.list_menu_play').trigger('click')
			}else{
				$('.list_music').eq(player.currentIndex).find('.list_menu_play').trigger('click')
			}
		})
		// 监听底部控制区域上一首按钮点击
		$('.music_pre').click(function(){
			$('.list_music').eq(player.preIndex()).find('.list_menu_play').trigger('click')
		})
		// 监听底部控制区域下一首按钮点击
		$('.music_next').click(function(){
			$('.list_music').eq(player.nextIndex()).find('.list_menu_play').trigger('click')
		})
		// 监听删除按钮的点击
		$('.content_list').delegate('.list_menu_del','click',function(){
			var $item = $(this).parents('.list_music')
			// 判断当前删除的是否为正在播放的
			if($item.get(0).index == player.currentIndex){
				$('.music_next').trigger('click')
			}
			$item.remove()
			player.changeMusic($item.get(0).index)
			// 重新排序
			$('.list_music').each(function(index,ele){
				ele.index = index
				$(ele).find('.list_number').text(index + 1)
			})
		})
		// 监听播放进度
		player.musicTimeUpdate(function(currentTime,duration,timeStr){
			// 同步时间
			$('.music_progress_time').text(timeStr)
			// 同步进度条
			// 计算播放比例
			var value = currentTime / duration * 100
			progress.setProgress(value)
			var index = lyric.currentIndex(currentTime)
			var $item = $('.song_lyric li').eq(index)
			$item.addClass('cur')
			$item.siblings().removeClass('cur')
			
			if(index <= 2) return;
			$('.song_lyric').css({
				marginTop:(-index +2) * 30
			})
		})
		// 监听声音按钮的点击
		$('.music_voice_icon').click(function(){
			$(this).toggleClass('music_voice_icon2')
			if($(this).attr('class').indexOf('music_voice_icon2') != -1){
				player.musicVoiceSeekTo(0)
			}else{
				player.musicVoiceSeekTo(1)
			}
		})
	}
	// 加载歌曲列表
	getPlayerList()
	
	function createMusicItem(index,music){
		var $item = $(`<li class="list_music">
								<div class="list_check"><i></i></div>
								<div class="list_number">${index+1}</div>
								<div class="list_name">${music.musicname}
									<div class="list_menu">
										<a href="javascript:;" title="播放" class="list_menu_play"></a>
										<a href="javascript:;" title="添加"></a>
										<a href="javascript:;" title="下载"></a>
										<a href="javascript:;" title="分享"></a>
									</div>
								</div>
								<div class="list_singer">${music.singerid}</div>
								<div class="list_time">
									<span>${music.musictime}</span>
									<a href="javascript:;" title="删除" class="list_menu_del"></a>
								</div>
							</li>`)
		$item.get(0).index = index;
		$item.get(0).music = music;
		return $item;
	}
})