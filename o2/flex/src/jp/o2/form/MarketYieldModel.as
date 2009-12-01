package jp.o2.form
{
    import mx.collections.ArrayCollection;

    [Bindable]
    public class MarketYieldModel
    {
        public var yieldList:ArrayCollection = new ArrayCollection([{label: "JPY", data: "JPY"}, {label: "USD", data: "USD"}]);

        public var yieldCurve:ArrayCollection = new ArrayCollection([{term: "1Y", rate: 0.1}, {term: "2Y", rate: 0.5}, {term: "3Y",
                rate: 0.2}, {term: "4Y", rate: 0.5}]);

        public function MarketYieldModel()
        {
        }
    }
}