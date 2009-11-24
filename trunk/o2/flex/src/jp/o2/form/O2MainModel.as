package jp.o2.form
{
    import mx.collections.ArrayCollection;
    import mx.collections.XMLListCollection;

    [Bindable]
    public class O2MainModel
    {
        public var menuBarCollection:XMLListCollection;

        private var menubarXML:XMLList=
            <>
                <menuitem label="Market" data="top">
                    <menuitem label="Yield" data="0"/>
                </menuitem>
                <menuitem label="Deal Entry" data="top">
                    <menuitem label="Interest Rate Swap" data="0"/>
                </menuitem>
                <menuitem label="Deal Processes" data="top">
                    <menuitem label="Rate Reset" data="0"/>
                </menuitem>
            </>
        ;

        public function O2MainModel()
        {
            menuBarCollection=new XMLListCollection(menubarXML);
        }
    }
}