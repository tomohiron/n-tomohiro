package jp.o2.form
{
    import flash.events.Event;
    import flash.events.MouseEvent;

    import mx.collections.ArrayCollection;
    // import mx.collections.ItemResponder;
    import mx.controls.Alert;
    import mx.core.IMXMLObject;
    import mx.events.FlexEvent;
    import mx.rpc.AsyncToken;
    import mx.rpc.AsyncResponder;
    import mx.rpc.events.FaultEvent;
    import mx.rpc.events.ResultEvent;
    import mx.rpc.remoting.RemoteObject;

    public class MarketYieldController implements IMXMLObject
    {
        private var view:MarketYield;
        private var service:RemoteObject;

        public function MarketYieldController()
        {
        }

        public function initialized(document:Object, id:String):void
        {
            view = document as MarketYield;
            view.addEventListener(FlexEvent.CREATION_COMPLETE, creationComleteHandler);

            service = new RemoteObject("marketYieldService");
        }

        public function creationComleteHandler(event:Event):void
        {
            view.graphButton.addEventListener(MouseEvent.CLICK, graphButtonClickHandler);
        }

        public function graphButtonClickHandler(event:MouseEvent):void
        {
            doGraph();
        }

        private function doGraph():void
        {
//            var yieldIDs:Array=["m06", "y01", "y01_5", "y02", "y03", "y04", "y05"];
//            for each (var yieldID:String in yieldIDs)
//            {
//                var yield:Object={id: yieldID, data: Number(view.get(yieldID))};
//            }

            var yieldArray:Array=new Array();
            yieldArray.push({id: "m06",   year: "0.5", rate: view.m06.text  });
            yieldArray.push({id: "y01",   year: "1.0", rate: view.y01.text  });
            yieldArray.push({id: "y01_5", year: "1.5", rate: view.y01_5.text});
            yieldArray.push({id: "y02",   year: "2.0", rate: view.y02.text  });
            yieldArray.push({id: "y03",   year: "3.0", rate: view.y03.text  });
            yieldArray.push({id: "y04",   year: "4.0", rate: view.y04.text  });
            yieldArray.push({id: "y05",   year: "5.0", rate: view.y05.text  });

            var token:AsyncToken=service.getNSplineCoef(yieldArray);
            token.addResponder(new AsyncResponder(successOfGetNSplineCoef, function(e:FaultEvent, obj:Object=null):void
                {
                    Alert.show("fail at doGraph() : " + e.message);
                }));
        }
        
        private function successOfGetNSplineCoef(e:ResultEvent, obj:Object=null):void
        {
        // TODO
            var coef:ArrayCollection = e.result as ArrayCollection;
            
            var x0:Array = [ 0.5, 1.0, 2.0 ];
            
            var max:Number = 5.0;
            var ratio:Number = 100.0;

            var array:Array = new Array();
            for (var i:Number = 0; i <= max * ratio; i += 1) {
                var year:Number = i / ratio;
                var term:String = "";
                if (i % ratio == 0) {
                    term = year.toFixed(1) + "Y";
                }
                
                var x:Number = i / ratio;
                var y:Number = nSpline(x0, coef.source, x);
                array.push({term: term, rate: y});
            }

            view.model.yieldCurve = new ArrayCollection(array);
        }
                
        private function nSpline(x0:Array, coef:Array, x:Number):Number {
            var y:Number = coef[0] + coef[1] * x;
            for ( var j:int = 0; j < x0.length; j++  ) {
                y += coef[j+2] * Math.pow( Math.max( x - x0[j], 0.0 ), 3.0);
            }
            return y;
        }
    }
}