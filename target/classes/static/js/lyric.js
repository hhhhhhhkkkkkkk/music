(function(window){
	function Lyric(path){
		return new Lyric.prototype.init(path)
	}
	Lyric.prototype = {
		constructor : Lyric,
		musicList : [],
		init:function(path){
			this.path = path
		},
		times:[],
		lyrics:[],
		index:-1,
		loadLyric:function(callBack){
			var $this = this
			$.ajax({
				url:"data:text/plain;base64,"+$this.path,
				dataType:"text",
				success:function(data){
					$this.parseLyric(data)
					callBack()
				},
				error:function(e){
					console.log(e)
				}
			})
		},
		parseLyric:function(data){
			var $this = this
			$this.times = []
			$this.lyrics = []
			var array = data.split('\n')
			var timeReg = /\[(\d*:\d*\.\d*)\]/
			$.each(array,function(index,ele){
				var lrc = ele.split(']')[1]
				if(lrc.length == 1) return true;
				$this.lyrics.push(lrc)
				var res = timeReg.exec(ele)
				if(res == null) return true;
				var timeStr = res[1]
				var res2 = timeStr.split(':')
				var min = parseInt(res2[0])*60
				var sec = parseFloat(res2[1])
				var time = parseFloat(Number(min + sec).toFixed(2))
				$this.times.push(time)
			})
		},
		currentIndex:function(currentTime){
			if(currentTime >= this.times[0]){
				this.index++
				// 删除数组最前面的第一个元素
				this.times.shift()
			}
			return this.index
		}
	}
	Lyric.prototype.init.prototype = Lyric.prototype
	window.Lyric = Lyric
})(window)