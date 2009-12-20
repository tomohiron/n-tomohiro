package jp.o2.form {
    import flash.events.Event;
    import flash.events.MouseEvent;

    import mx.collections.ArrayCollection;
    import mx.controls.Alert;
    import mx.core.IMXMLObject;
    import mx.events.FlexEvent;
    import mx.rpc.AsyncToken;
    import mx.rpc.AsyncResponder;
    import mx.rpc.events.FaultEvent;
    import mx.rpc.events.ResultEvent;
    import mx.rpc.remoting.RemoteObject;

    public class MarketYieldController implements IMXMLObject {
    
        private var view:MarketYield;
        private var service:RemoteObject;

        public function MarketYieldController() {
        }

        public function initialized(document:Object, id:String):void {
            view = document as MarketYield;
            view.addEventListener(FlexEvent.CREATION_COMPLETE, creationComleteHandler);

            service = new RemoteObject("marketYieldService");
        }

        public function creationComleteHandler(event:Event):void {
            view.graphButton.addEventListener(MouseEvent.CLICK, graphButtonClickHandler);
            doGraph();
        }

        public function graphButtonClickHandler(event:MouseEvent):void {
            doGraph();
        }

        public function doGraph():void {
            var yieldArray:Array = new Array();
            yieldArray.push({id: "m06",   year: "0.5", rate: view.m06.text  });
            yieldArray.push({id: "y01",   year: "1.0", rate: view.y01.text  });
            yieldArray.push({id: "y01_5", year: "1.5", rate: view.y01_5.text});
            yieldArray.push({id: "y02",   year: "2.0", rate: view.y02.text  });
            yieldArray.push({id: "y03",   year: "3.0", rate: view.y03.text  });
            yieldArray.push({id: "y04",   year: "4.0", rate: view.y04.text  });
            yieldArray.push({id: "y05",   year: "5.0", rate: view.y05.text  });

            var token:AsyncToken = service.getNSplineValues(yieldArray);
            token.addResponder(
                new AsyncResponder(
                    function(e:ResultEvent, obj:Object=null):void {
                        view.model.yieldCurve = e.result as ArrayCollection;
                    },
                    function(e:FaultEvent, obj:Object=null):void {
                        Alert.show("fail at getNSplineValue() : " + e.message);
                    }
                )
            );
        }
        
    }
}