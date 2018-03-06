

/* kesion 前端常用组件封装
 * kesion.assembly.js
 * Date: 2017-5-24 
 * 厦门科汛软件有限公司　 版权所有 © 2006-2017
 * 官方地址：http://www.kesion.com
 * 
 * 24小时服务热线：400-008-0263
 * 
 */
 
;(function ($) {
	
	$.fn.carousel = function(parameter){
		var $this = $(this);
		var width = parameter.width;
		var height = parameter.height;
		var axis = parameter.axis;
		var arrow = parameter.arrow;
		var autoPlay = parameter.autoPlay;
		var object = parameter.object;
		var objectWidth = parameter.objectWidth;
		var objectHeight = parameter.objectHeight;
		var initialObjWidth = parameter.objectWidth; //存储旧数据，用于检测是否设置百分比
		var initialObjHeight = parameter.objectHeight;//存储旧数据，用于检测是否设置百分比
		var scrollType = parameter.scrollType;
		var dots = parameter.dots;
		var number = parameter.number;
		var speed = parameter.speed;
		var interval = parameter.interval;
		var size = $this.find(object).size();
		var index = 0;
		var playFlag = true;
		var initialWidth = parameter.width; //存储初始数据，用于检测是否设置百分比
		var initialHeight = parameter.height;//存储初始数据，用于检测是否设置百分比
		if($this.css('position')=='static'){
			$this.css({position:'relative'});
		};
		if(scrollType!='slide'&&scrollType!='fade'){
			scrollType = 'slide';
		};
		if(!speed){
			speed = 400;
		};
		if(axis!='X'&&axis!='Y'){
			axis = 'X';
		};
		
		
		$this.css({width:width,overflow:'hidden'});		
		
		if(initialWidth.indexOf('%')!='-1'){
			width = $this.width();
		};
		if(initialHeight.indexOf('%')!='-1'){
			var heightRatio = initialHeight.replace(/%/,'')/100;
			height = width*heightRatio;
		};
		$this.css({height:height});
		
		if(objectWidth){
			if(initialObjWidth.indexOf('%')!='-1'){
				var objectWidthRatio = initialObjWidth.replace(/%/,'')/100;
				objectWidth = $this.width()*objectWidthRatio;
			};
		}else{
			objectWidth = $this.width();
		};
		
		if(objectHeight){
			if(initialObjHeight.indexOf('%')!='-1'){
				var objectHeightRatio = initialObjHeight.replace(/%/,'')/100;
				objectHeight = $this.height()*objectHeightRatio;
			};
		}else{
			objectHeight = $this.height();
		};
		
		
		if(scrollType=='slide'){
			if(axis=='X'){
				$this.wrapInner('<div class="carousel-scroll" style="width:99999px"></div>');
				$this.find(object).css({width:objectWidth,height:objectHeight,float:'left',overflow:'hidden'});
			}else if(axis=='Y'){
				$this.wrapInner('<div class="carousel-scroll" style="height:99999px"></div>');
				$this.find(object).css({width:objectWidth,height:objectHeight,display:'block',overflow:'hidden'});
			};
			var $carouselScroll = $this.find('.carousel-scroll');
			
		}else if(scrollType=='fade'){
			$this.find(object).css({width:width,height:height,overflow:'hidden',display:'none'});
			$this.find(object).first().show();
		};
		
		if(initialWidth.indexOf('%')!='-1'){
			$(window).resize(function(){
				$this.css({width:initialWidth});	
				width = $this.width();
				$this.css({width:width});	
				if(heightRatio){
					height = width*heightRatio;
					$this.css({height:height});
				};
				if(initialObjWidth){
					if(objectWidthRatio){
						objectWidth = width*objectWidthRatio;
					}else{
						objectWidth = initialObjWidth;
					};
				}else{
					objectWidth = $this.width();
				};
				
				if(initialObjHeight){
					if(objectHeightRatio){
						objectHeight = height*objectHeightRatio;
					}else{
						objectHeight = initialObjHeight;
					};
				}else{
					objectHeight = $this.height();
				};
								
				$this.find(object).css({width:objectWidth,height:objectHeight});
			});
		};

		//是否需要小圆点
		if(dots){
			if(scrollType=='slide'){
				$this.find(object).each(function() {
				   $(this).attr('carousel-id',$(this).index());
				});
			};
			$this.append('<div class="carousel-dots"><span></span></div>');
			if(dots==2){
				for(i=0;i<size;i++){
					var num = i+1;
					$this.find('.carousel-dots span').append('<i>'+num+'</i>');
				};
			}else if(dots==1){
				for(i=0;i<size;i++){
					$this.find('.carousel-dots span').append('<i></i>');
				};
			};
			$this.find('.carousel-dots').find('i:first').addClass('on');
			$this.find('.carousel-dots').on('click','i',function(){
				if(playFlag==true){
					playFlag=false;
					var oldIndex = $this.find('.carousel-dots').find('i.on').index();
					index = $(this).index();
					$(this).addClass('on').siblings().removeClass('on');
					
					if(scrollType=='slide'){
						
						
						var $thisObject = $this.find(''+object+'[carousel-id="'+index+'"]');
						var prevObjectSize = $thisObject.prevAll(object).size();
						
						var $nextObject = $thisObject.nextAll(object);
						
						if(index==oldIndex){
							//相同不执行任何操作
							playFlag=true
							
						}else if(index>oldIndex){
							if(prevObjectSize>0){
								
								//向右、上滚 
								if(axis=='X'){
									var scrollWidth = objectWidth*prevObjectSize;
									$carouselScroll.animate({marginLeft:'-'+scrollWidth+'px'},speed);
								}else if(axis=='Y'){
									var scrollHeight = objectHeight*prevObjectSize;
									$carouselScroll.animate({marginTop:'-'+scrollHeight+'px'},speed);
								};
								setTimeout(function(){
									playFlag=true;
									
									for(p=prevObjectSize-1;p>=0;p--){
										$($this.find(''+object+'[carousel-id="'+index+'"]').prevAll(object)[p]).insertAfter($this.find(object).last());
									};
									if(axis=='X'){
										$carouselScroll.animate({marginLeft:'0px'},0);
									}else if(axis=='Y'){
										$carouselScroll.animate({marginTop:'0px'},0);
									};
								},speed+50);
							};
						}else{
							
							//向左、下滚
							$nextObject.insertBefore($this.find(object).first());
							$thisObject.insertBefore($this.find(object).first());
							if(axis=='X'){
								var scrollWidth = objectWidth*($nextObject.size()+1);
								$carouselScroll.css({marginLeft:'-'+scrollWidth+'px'});
								$carouselScroll.animate({marginLeft:'0px'},speed);

							}else if(axis=='Y'){
								var scrollHeight = objectHeight*($nextObject.size()+1);
								$carouselScroll.css({marginTop:'-'+scrollHeight+'px'});
								$carouselScroll.animate({marginTop:'0px'},speed);
							};
							setTimeout(function(){
								playFlag=true;								
							},speed+50);
							
							
						};//slide
					}else if(scrollType=='fade'){
						$this.find(object).eq(index).fadeIn(speed).siblings().stop().hide();
						setTimeout(function(){
							playFlag=true;								
						},speed);
					};//fade
				};
				
			});
			
		};
			
		//是否需要左右按钮
		if(arrow==true){
			$this.append('<div class="carousel-prev"></div><div class="carousel-next"></div>');
			
			$this.on('click','.carousel-prev',function(){
				index = $this.find('.carousel-dots .on').index();
				if(size>1){
					if(playFlag==true){
						playFlag=false;
						index--;
						if(index<0){
							index = size-1;
						};
						if(dots==true){
							$this.find('.carousel-dots').find('i:eq('+index+')').addClass('on').siblings().removeClass('on');
						};
						if(scrollType=='slide'){
							
							$this.find(object).last().insertBefore($this.find(object).first());
							if(axis=='X'){
								$carouselScroll.css({marginLeft:'-'+objectWidth+'px'});
								$carouselScroll.animate({marginLeft:'0px'},speed);
							}else if(axis=='Y'){
								$carouselScroll.css({marginTop:'-'+objectHeight+'px'});
								$carouselScroll.animate({marginTop:'0px'},speed);
							};
						}else if(scrollType=='fade'){
							$this.find(object).eq(index).fadeIn(speed).siblings().stop().hide();
						};
						setTimeout(function(){
							playFlag=true;
						},speed+50);
					};
				};
			
			});
			$this.on('click','.carousel-next',function(){
				
				nextClick();
				
			});
			
			
		};
		
		//是否自动播放
		if(autoPlay==true){
			if(!interval){
				interval = 4000;
			};
			var autoPlayFlag = true;

			setInterval(autoPlay,interval)
			function autoPlay(){
				if(autoPlayFlag==true){
					nextClick();
				};
				
			};
			$this.mousemove(function(){
				autoPlayFlag = false;
			});
			$this.mouseout(function(){
				autoPlayFlag = true;
			});
		};

		
		function nextClick(){
			index = $this.find('.carousel-dots .on').index();
			if(size>1){
				if(playFlag==true){
					playFlag=false;
					index++;
					if(index>=size){
						index = 0;
					};
					if(dots){
						$this.find('.carousel-dots').find('i:eq('+index+')').addClass('on').siblings().removeClass('on');
					};
					if(scrollType=='slide'){
						if(axis=='X'){
							$carouselScroll.animate({marginLeft:'-'+objectWidth+'px'},speed);
						}else if(axis=='Y'){
							$carouselScroll.animate({marginTop:'-'+objectHeight+'px'},speed);
						};
					}else if(scrollType=='fade'){
						$this.find(object).eq(index).fadeIn(speed).siblings().stop().hide();
					};
					setTimeout(function(){
						if(scrollType=='slide'){
							$this.find(object).first().insertAfter($this.find(object).last());
							if(axis=='X'){
								$carouselScroll.animate({marginLeft:'0px'},0);
							}else if(axis=='Y'){
								$carouselScroll.animate({marginTop:'0px'},0);
							};
						};
						playFlag=true;
					},speed+50);
				};
			};
		};
		
	};


	$.fn.tab = function(parameter){
		var $this = $(this);
		var tabName = parameter.tabName;
		var boxName = parameter.boxName;
		var tabEvent = parameter.tabEvent;
		var type = parameter.type;
		if(!tabEvent){
			tabEvent = 'click';
		};
		if(!type){
			type = 'normal';
		};
		
		
		$this.each(function(){
			var $tabName = $(this).find(tabName);
			var $boxName = $(this).find(boxName);
			$tabName.first().addClass('active').siblings().removeClass('active');
			$boxName.first().show().siblings(boxName).hide();
			
			$tabName.on(tabEvent,function(){
				var index = $(this).index();
				$(this).addClass('active').siblings().removeClass('active');
				if(type=='normal'){
					$boxName.eq(index).show().siblings(boxName).hide();
				}else if(type=='fade'){
					$boxName.eq(index).fadeIn().siblings(boxName).stop().hide();
				};
			});
		
		});
		
	};

	$.fn.nesting = function(parameter){
		var $this = $(this);
		var tagName = parameter.tagName;
		var nestedName = parameter.nestedName.split(',');
		var loopNum = parameter.loopNum;
		var size = $this.find(tagName).size();
		var frequency = Math.ceil(size/loopNum);
		var nestedTag = nestedName[0];
		var nestedTagName = nestedName[1];
		var tagId = -1;
		$this.find(tagName).each(function(){
			tagId++;
			$(this).attr('tag-id',tagId);
		});
		
		if($.trim(nestedTagName)==''||!nestedTagName){
			var appendTag = '<'+nestedTag+' nesting></'+nestedTag+'>';
		}else{
			var appendTag = '<'+nestedTag+' class="'+nestedTagName+'" nesting></'+nestedTag+'>';
		};
		for(f=0;f<frequency;f++){
			$this.append(appendTag);
			
			for(s=f*loopNum;s<f*loopNum+loopNum;s++){
				
				$this.find(tagName+'[tag-id="'+s+'"]').appendTo($this.find('[nesting]').eq(f))
				
			};
		};
		
		
	};
	
	$.fn.aspectRatio = function(parameter){
		var $this = $(this);
		var set = parameter.set;
		$this.each(function(){
			$(this).height($(this).width()*set);
			$(window).resize(function(){
				$(this).height($(this).width()*set);
			});
		});
	};
	
	
})(jQuery);