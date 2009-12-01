package jp.o2.form
{
    import flash.events.Event;
    import flash.events.MouseEvent;

    import mx.collections.ArrayCollection;
    import mx.collections.ItemResponder;
    import mx.controls.Alert;
    import mx.core.IMXMLObject;
    import mx.events.FlexEvent;
    import mx.rpc.AsyncToken;
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
            var max:Number = 10.0;
            var ratio:Number = 100.0;

            var array:Array = new Array();
            for (var i:Number = 0; i <= max * ratio; i += 1)
            {
                var year:Number = i / ratio;
                var term:String = "";
                if (i % ratio == 0)
                {
                    term = year.toFixed(1);
                }
                array.push({term: term, rate: Math.pow(year - 2.0, 2.0) - 2.0});
            }

            view.model.yieldCurve = new ArrayCollection(array);

//            var yieldIDs:Array=["m06", "y01", "y01_5", "y02", "y03", "y04", "y05"];
//            for each (var yieldID:String in yieldIDs)
//            {
//                var yield:Object={id: yieldID, data: Number(view.get(yieldID))};
//            }

//            var yieldArray:Array=new Array();
//            yieldArray.push({id: "m06", rate: Number(view.m06.text)});
//            yieldArray.push({id: "y01", rate: Number(view.y01.text)});
//
//            var token:AsyncToken=service.getNSpline(yieldArray);
//            token.addResponder(new ItemResponder(function(e:ResultEvent, obj:Object=null):void
//                {
//                // TODO
//                }, function(e:FaultEvent, obj:Object=null):void
//                {
//                    Alert.show("fail at doGraph() : " + e.message);
//                }));
        }
    }
}