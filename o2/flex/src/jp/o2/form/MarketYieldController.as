package jp.o2.form
{
    import flash.events.Event;
    import flash.events.MouseEvent;

    import mx.collections.ArrayCollection;
    import mx.collections.ItemResponder;
    import mx.controls.Alert;
    import mx.core.IMXMLObject;
    import mx.events.FlexEvent;
    import mx.events.MenuEvent;
    import mx.rpc.AsyncToken;
    import mx.rpc.events.FaultEvent;
    import mx.rpc.events.ResultEvent;

    public class MarketYieldController implements IMXMLObject
    {
        private var view:MarketYield;

        public function MarketYieldController()
        {
        }

        public function initialized(document:Object, id:String):void
        {
            view=document as MarketYield;
            view.addEventListener(FlexEvent.CREATION_COMPLETE, creationComleteHandler);
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
            yieldArray.push({id: "m06", rate: Number(view.m06.text)});
            yieldArray.push({id: "y01", rate: Number(view.y01.text)});

            var token:AsyncToken=view.service.getNSpline(yieldArray);
            token.addResponder(new ItemResponder(function(e:ResultEvent, obj:Object=null):void
                {
                // TODO
                }, function(e:FaultEvent, obj:Object=null):void
                {
                    Alert.show("fail at doGraph() : " + e.message);
                }));
        }

        private function doEntry():void
        {
//            var newTerms:CommodityCapTermsDto=new CommodityCapTermsDto();
//            newTerms.notional=Number(view.notionalField.text);
//            newTerms.commodity=view.commodityCombo.selectedItem.data;
//            newTerms.commoditySource=view.commoditySourceField.text;
//            newTerms.currency=view.currencyCombo.selectedItem.data;
//            newTerms.strike=Number(view.strikeField.text);
//            newTerms.startDate=view.startDateField.selectedDate;
//            newTerms.endDate=view.endDateField.selectedDate;
//            newTerms.frequency=view.frequencyCombo.selectedItem.data;
//            newTerms.odd=view.oddCombo.selectedItem.data;
//            newTerms.dateRolling=view.dateRollingCombo.selectedItem.data;
//            newTerms.calendar=view.calendarCombo.selectedItem.data;
//            newTerms.premium=Number(view.premiumField.text);
//
//            var token:AsyncToken=view.service.entryDeal(newTerms);
//            token.addResponder(new ItemResponder(function(e:ResultEvent, obj:Object=null):void
//                {
//                // TODO
//                }, function(e:FaultEvent, obj:Object=null):void
//                {
//                    Alert.show("fail at entryDeal() : " + e.message);
//                }));
        }
    }
}