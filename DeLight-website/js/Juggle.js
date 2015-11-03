(function($){
	$.fn.SiteswapGenerator = function(){
		//div, на котором вызван генератор
		var $container = this;
		//Main Object of generator
		var animator = {
			//resolution == steps in HTML
			resolution: 20,
			contWidth: parseInt($container.width()),
			contHeight: parseInt($container.height()),
			//speed for setInterval in ms
			speed: 10,
			ballSize: 25,
			ballImage: $("<img />").attr({
				src: "../img/ball.gif",
				class: "ball"
			}),
			dwell: 0.5,
			dwellSteps: 0,
			//Siteswap for animate
			ssw: "0",
			ballsNumber: 3,
			timer: null,
			balls: new Array(),
			step: 0,
			sswStep: 0,
			direction: new Array(100,0,50,0),
			H: 4,
			maxThrow: 0,
			yCoord: 30,

			startJuggling: function(){
				this.dwellSteps = Math.round(this.dwell * this.resolution)
				this.setSiteswap();
				this.timer = setInterval(this.animate(), this.speed);
			},

			stopJuggling: function(){
				clearInterval(this.timer);
			},

			createBalls: function(){
				this.balls = new Array(this.ballsNumber);
				for (var i = 0; i < this.ballsNumber; i++) {
					this.balls[i] = new Ball(this, $container, i);
				}
			},

			/**
			 * Creates and starts the siteswap animation
			 * Resolution == steps in HTML
			 */
			setSiteswap: function(){
				this.stopJuggling();
				this.destroyBalls();
				if($("#normal").prop("checked"))
					this.direction = new Array(50,0,100,0);
				else this.direction = new Array(100,0,50,0);

				var siteswap = $("#ssw").val();

				if (this.checkSiteswap(siteswap) == false) alert(siteswap+" is no valid pattern!");
				else {
					this.ssw = siteswap;
					//Control throw height
					this.H = 4;
					var h = Math.sqrt((this.contHeight - this.yCoord - this.ballSize)/
						((this.maxThrow - 1.0)*(this.maxThrow - 1.0)));
					if (h < this.H) this.H = h;
					this.createBalls();
					this.step = 0;
					this.sswStep = 0;
				}
			},

			destroyBalls: function(){
				if (this.balls !== null) {
					delete this.balls;
				}
			},

			/**
			 * Check siteswap and calculate number of balls
			 * and the maximal height
			 */
			checkSiteswap: function(siteswap){
				if (siteswap.length == 0) return false;
				if (!Number.isInteger(parseInt(siteswap))) return false;
				this.maxThrow = 0;
				var s = 0;
				for (var i = 0; i < siteswap.length; i++) {
					var ch = parseInt(siteswap.charAt(i));
					s += ch;
					if (ch > this.maxThrow) this.maxThrow = ch;
				}
				this.ballsNumber = s / siteswap.length;
				if(this.ballsNumber != 0 && Number.isInteger(this.ballsNumber))
					return true;
			},

			animate: function(){
				if (this.step < this.resolution == 0) {
					var height = this.getHeight(this.sswStep);
					if (height != 0) {
						var i = this.getNextBall();
						if (i == -1){
							this.stopJuggling();
							alert ("Fehler");
							return;
						}
						this.balls[i].ssw = height;
						this.balls[i].step = 0;
						if (this.sswStep % 2 == 0) this.balls[i].hand = -1;
						else this.balls[i].hand = 1;
					}
					this.sswStep++;
				}
				for (var i = 0; i < this.ballsNumber; i++) {
					this.balls[i].animateMe();
				}
				this.step++;
			},

			getHeight: function(position){
				return parseInt(this.ssw.charAt(position % this.ssw.length));
			},

			getNextBall: function(){
				for (var i = 0; i < this.ballsNumber; i++){
					if (this.balls[i].step == this.balls[i].ssw * this.resolution) return i;
				}
				return -1;
			}

		}

		function Ball(animator, $viewport, i){
			this.image = animator.ballImage;
			$viewport.append(this.image);
			this.step = 0;
			this.id = "ball" + i;
			this.ssw = 0;
			this.hand = -1;
			this.timer = null;
			this.animateMe = function(){
				if (this.step == 0) this.image.css('display', 'inline-block')
				var sideLength = (animator.contWidth - animator.ballSize)/2;
				var start = sideLength - (animator.direction[0] * sideLength)/100;
				var end = sideLength + (animator.direction[0] * sideLength)/100;
				var startToss = sideLength - (animator.direction[2] * sideLength)/100;
				if (this.hand == 1) {
					var temp = start;
					start = end;
					end = temp;
					startToss = sideLength + (animator.direction[2] * sideLength)/100;
				}
				if (this.ssw % 2 == 0) end = start;
				var pos;
				if (this.step < animator.dwellSteps)
					pos = interpolateBezier(this.step, animator.dwellSteps, start, 
						animator.yCoord + animator.direction[1], 
						startToss, animator.yCoord + animator.direction[3], -30);
				else 
					pos = getThrowCoord(this.step - animator.dwellSteps, 
						this.ssw * animator.resolution - animator.dwellSteps, animator.H, 
						startToss, animator.yCoord + animator.direction[3], end, 
						animator.yCoord + animator.direction[1], this.ssw);
				var position = this.image.position();
				this.image.offset({left: (position.left + pos[0]), top: (position.top - pos[1])});
				this.step++;
			}
		}

		/**
		 * Static function
		 */
		function interpolateBezier(step, cStep, startx, starty, endx, endy, oben) {
			var t = step / (cStep * 1.0);
			var u = 1 - t;
			var tuTriple = 3 * t * u;
			var c0 = u * u * u;
			var c1 = tuTriple * u;
			var c2 = tuTriple * t;
			var c3 = t * t * t;
			x = (startx + endx) / 2.0;
			y = (starty + endy) / 2.0 + oben;
			x = c0 * startx + c1 * x + c2 * x + c3 * endx;
			y = c0 * starty + c1 * y + c2 * y + c3 * endy;
			var pos = new Array(2);
			pos[0] = x; pos[1] = y;
			return pos;
		}

		/**
		 * Static function
		 */
		function getThrowCoord (step, cStep, T, startx, starty, endx, endy, height) {
			var t = (height - 1.0) * T;
			if (height == 3) t = (3.5 - 1.0) * T;
			var h = (9.81 * t * t) / 10.0;
			var st = 1.0*step / cStep;
			x = startx + st * (endx - startx);
			y = starty + (st * (endy - starty)) - 4.0 * h * (st * st - st);
			var pos = new Array(2);
			pos[0] = x;
			pos[1] = y;
			return pos;
		}

		$("#start").click(function(){
			animator.startJuggling();
		});

	}
})(jQuery);