package gab.cdi.teloe.main.teleo_fragments

import gab.cdi.teleo.main.teleo_fragments.FragmentHome
import gab.cdi.teleo.main.teleo_fragments.FragmentRegions
import gab.cdi.teleo.main.teleo_fragments.FragmentTrendingNow
import gab.cdi.teleo.main.teleo_fragments.FragmentWhatsOn

/**
 * Created by Default on 10/07/2018.
 */
interface FragmentTeleo : FragmentHome.OnFragmentInteractionListener, FragmentWhatsOn.OnFragmentInteractionListener, FragmentTrendingNow.OnFragmentInteractionListener, FragmentRegions.OnFragmentInteractionListener{
}