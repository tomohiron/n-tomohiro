package jp.o2.form {
    import flash.events.Event;
    import flash.events.MouseEvent;
    
    import mx.controls.Alert;
    import mx.core.IMXMLObject;
    import mx.events.FlexEvent;
    import mx.events.MenuEvent;

    public class O2MainController implements IMXMLObject {
        private var view:O2Main;

        public function O2MainController() {
        }

        public function initialized(document:Object, id:String):void {
            view = document as O2Main;
            view.addEventListener(FlexEvent.CREATION_COMPLETE, creationComleteHandler);
        }

        public function menuHandler(event:MenuEvent):void {
            if (event.item.@data != "top") {
                view.viewStack.selectedIndex = event.item.@data;
            }
        }

        public function creationComleteHandler(event:Event):void {
//            view.entryButton.addEventListener(MouseEvent.CLICK, entryButtonClickHandler);
        }

        public function entryButtonClickHandler(event:MouseEvent):void {
            doEntry();
        }

        public function performButtonClickHandler(event:MouseEvent):void {
            doPerform();
        }

        private function doEntry():void {
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

        private function doPerform():void {
            Alert.show("doPerform()");
        }
    }
}