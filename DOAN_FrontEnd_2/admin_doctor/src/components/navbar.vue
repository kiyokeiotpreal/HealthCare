<template>
    <Disclosure as="nav" class="" style="background-color: rgb(237, 255, 250); font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif !important; " v-slot="{ open }">


      <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <div class="relative flex  items-center justify-between">
          <div class="row flex flex-1 items-center  sm:items-stretch sm:justify-start">

            <div class="flex flex-shrink-0 items-center justify-start col-sm-6 col-xl-3 col-lg-3 col-md-6 ">
                <div class="inset-y-0 left-0 flex items-center">
                    <!-- Mobile menu button-->
                    <!-- <DisclosureButton class="relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
                    <span class="absolute -inset-0.5" />
                    <span class="sr-only">Open main menu</span>
                    <Bars3Icon v-if="!open" class="block h-6 w-6" aria-hidden="true" />
                    <XMarkIcon v-else class="block h-6 w-6" aria-hidden="true" />
                    </DisclosureButton> -->
                </div>
                <router-link to="/">
                  <img class="h-11 w-auto" src="logo.png" alt="Your logo" />
                </router-link>
            </div>

            <div class="col-xl-7 col-lg-6 col-md-8 col-12 row ">
              <div class=" col-xl-8 col-lg-12 col-md-12 " style="margin: auto;">
                <div class="flex space-x-4 ">
                  <a v-for="item in navigation" 
                  :key="item.name" :href="item.href" 
                  :class="[item.current ? 'bg-yellow-300 text-white' : 'text-gray-900 hover:bg-yellow-300 hover:text-white', 'rounded-xl px-3 py-2 text-md font-medium']" 
                  :aria-current="item.current ? 'page' : undefined" 
                  style="text-decoration: none;">{{ item.name }}</a>
                </div>
              </div>

                <div class="flex ml-6 py-3 col-xl-3 col-lg-6 col-md-5 col-sm-4 col-6 " style="padding: 0;">
                  <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                  <div class="input-group-text border-0 mx-2"  style="background-color: rgb(237, 255, 250);">
                      <i class="fas fa-search"></i>
                  </div>
                </div>

            </div>

            <div class="col-xl-2 col-lg-2 col-md-2 col-sm-2 col-12 flex flex-shrink-0 justify-start items-center ">
              <!-- <img class="h-11 w-auto" src="lichhen.png" alt="Your logo" /> -->
              <img class="h-11 w-auto" src="hotro.png" alt="Your logo" />

            </div>

          </div>

        </div>
      </div>
  
      <DisclosurePanel class="sm:hidden">
        <div class="space-y-1 px-2 pb-3 pt-2">
          <DisclosureButton v-for="item in navigation" :key="item.name" as="a" :href="item.href" :class="[item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white', 'block rounded-md px-3 py-2 text-base font-medium']" :aria-current="item.current ? 'page' : undefined">{{ item.name }}</DisclosureButton>
        </div>
      </DisclosurePanel>
    </Disclosure>
  </template>
  
  <script setup>
  import { Disclosure, DisclosureButton, DisclosurePanel, Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/vue'
  import { Bars3Icon, BellIcon, XMarkIcon } from '@heroicons/vue/24/outline'
  
  const navigation = [
    { name: 'Tất cả', href: '#', current: true },
    { name: 'Tại nhà', href: '#', current: false },
    { name: 'Tại viện', href: '#', current: false },
    { name: 'Sống khoẻ', href: '#', current: false },
  ]
  </script>